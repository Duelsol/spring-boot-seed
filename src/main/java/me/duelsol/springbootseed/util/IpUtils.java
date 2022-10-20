package me.duelsol.springbootseed.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author 冯奕骅
 */
public class IpUtils {

    private static final Pattern PATTERN = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

    private IpUtils() {}

    /**
     * 获取IP地址
     */
    public static String getRealIp(ServerHttpRequest request) {
        if (request == null) {
            return "";
        }
        String ip = request.getHeaders().getFirst("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeaders().getFirst("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            ip = Optional.ofNullable(request.getRemoteAddress())
                    .map(address -> address.getAddress().getHostAddress())
                    .orElse("");
            try {
                return "0:0:0:0:0:0:0:1".equals(ip) ? InetAddress.getLocalHost().getHostAddress() : ip;
            } catch (UnknownHostException e) {
                return ip;
            }
        }
    }

    /**
     * 判断是否是外网IP
     */
    public static boolean isExternalAddress(String ip) {
        if (StringUtils.isBlank(ip)) {
            return true;
        }
        String[] bits = ip.split("\\.");
        if (bits.length < 4) {
            return true;
        }
        if ("10".equals(bits[0])) {
            return false;
        } else if ("172".equals(bits[0])) {
            int second = Integer.parseInt(bits[1]);
            return second < 16 || second > 31;
        } else if ("192".equals(bits[0]) && "168".equals(bits[1])) {
            return false;
        }
        return true;
    }

    /**
     * 正则表达式校验IP
     */
    public static boolean isValid(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        return PATTERN.matcher(ip).matches();
    }

    /**
     * 	判断IP是否包含在白名单中
     */
    public static boolean isAllowed(String ip, String[] allowList) {
        if (!isValid(ip)) {
            return false;
        }
        if (ArrayUtils.isEmpty(allowList)) {
            return true;
        }
        Set<String> ipList = getAllowIpList(allowList);
        return isAllowed(ip, ipList);
    }

    /**
     * 从白名单获取可用的IP列表
     */
    private static Set<String> getAllowIpList(String[] allowList) {
        Set<String> ipList = new HashSet<>(allowList.length);
        for (String allow : allowList) {
            allow = allow.trim();
            // 处理通配符
            if (allow.contains("*")) {
                String[] ips = allow.split("\\.");
                String[] startParts = new String[] { "0", "0", "0", "0" };
                String[] endParts = new String[] { "255", "255", "255", "255" };
                List<String> range = new ArrayList<>();
                for (int i = 0; i < ips.length; i++) {
                    if (ips[i].contains("*")) {
                        range = range(ips[i]);
                        startParts[i] = null;
                        endParts[i] = null;
                    } else {
                        startParts[i] = ips[i];
                        endParts[i] = ips[i];
                    }
                }

                StringBuilder startIp = new StringBuilder();
                StringBuilder endIp = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    if (startParts[i] != null) {
                        startIp.append(startParts[i]).append(".");
                        endIp.append(endParts[i]).append(".");
                    } else {
                        startIp.append("[*].");
                        endIp.append("[*].");
                    }
                }
                startIp.deleteCharAt(startIp.length() - 1);
                endIp.deleteCharAt(endIp.length() - 1);

                for (String segment : range) {
                    String start = startIp.toString().replace("[*]", segment.split(";")[0]);
                    String end = endIp.toString().replace("[*]", segment.split(";")[1]);
                    if (isValid(start) && isValid(end)) {
                        ipList.add(start + "-" + end);
                    }
                }
            }
            // 处理网段 xxx.xxx.xxx.xxx/24
            else if (allow.contains("/")) {
                ipList.add(allow);
            }
            // 处理单个IP或者范围
            else if (isValid(allow)) {
                ipList.add(allow);
            }
        }
        return ipList;
    }

    /**
     * 对包含通配符的单个IP分段进行范围限定
     *
     * @param part 包含通配符的IP地址分段
     * @return 返回限定后的IP范围，格式为List[10;19, 100;199]
     */
    private static List<String> range(String part) {
        List<String> result = new ArrayList<>();
        int length = part.length();
        if (length == 1) {
            result.add("0;255");
        } else if (length == 2) {
            String s1 = range(part, 1);
            if (s1 != null) {
                result.add(s1);
            }
            String s2 = range(part, 2);
            if (s2 != null) {
                result.add(s2);
            }
        } else {
            String s1 = range(part, 1);
            if (s1 != null) {
                result.add(s1);
            }
        }
        return result;
    }

    private static String range(String part, int length) {
        String start;
        String end;
        if (length == 1) {
            start = part.replace("*", "0");
            end = part.replace("*", "9");
        } else {
            start = part.replace("*", "00");
            end = part.replace("*", "99");
        }
        if (Integer.parseInt(start) > 255) {
            return null;
        }
        if (Integer.parseInt(end) > 255) {
            end = "255";
        }
        return start + ";" + end;
    }

    private static boolean isAllowed(String ip, Set<String> ipList) {
        if (ipList.isEmpty() || ipList.contains(ip)) {
            return true;
        }
        for (String allow : ipList) {
            if (allow.contains("-")) {
                String[] temp = allow.split("-");
                String[] start = temp[0].split("\\.");
                String[] end = temp[1].split("\\.");
                String[] target = ip.split("\\.");
                boolean check = true;
                // 对IP从左到右进行逐段匹配
                for (int i = 0; i < 4; i++) {
                    int f = Integer.parseInt(start[i]);
                    int e = Integer.parseInt(end[i]);
                    int t = Integer.parseInt(target[i]);
                    if (!(f <= t && t <= e)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    return true;
                }
            } else if (allow.contains("/")) {
                int splitIndex = allow.indexOf("/");
                // 子网段
                String ipSegment = allow.substring(0, splitIndex);
                // 掩码
                String netmask = allow.substring(splitIndex + 1);
                // ip转二进制
                long ipLong = ipToLong(ip);
                // 子网段二进制
                long maskLong = (2L << 32 - 1) - (2L << 32 - Integer.parseInt(netmask) -1);
                // ip与和子网段相与 得到 网络地址
                String calcSegment = longToIp(ipLong & maskLong);
                // 如果计算得出网络地址和库中网络地址相同 则合法
                if (ipSegment.equals(calcSegment)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static long ipToLong(String ip) {
        long[] parts = new long[4];
        // 先找到IP地址字符串中.的位置
        int part1 = ip.indexOf(".");
        int part2 = ip.indexOf(".", part1 + 1);
        int part3 = ip.indexOf(".", part2 + 1);
        // 将每个.之间的字符串转换成整型
        parts[0] = Long.parseLong(ip.substring(0, part1));
        parts[1] = Long.parseLong(ip.substring(part1 + 1, part2));
        parts[2] = Long.parseLong(ip.substring(part2 + 1, part3));
        parts[3] = Long.parseLong(ip.substring(part3 + 1));
        return (parts[0] << 24) + (parts[1] << 16) + (parts[2] << 8) + parts[3];
    }

    /**
     * 将十进制整数形式转换成127.0.0.1形式的IP地址
     */
    private static String longToIp(long longIp) {
        // 直接右移24位
        return "" + (longIp >>> 24) +
                "." +
                // 将高8位置0，然后右移16位
                ((longIp & 0x00FFFFFF) >>> 16) +
                "." +
                ((longIp & 0x0000FFFF) >>> 8) +
                "." +
                (longIp & 0x000000FF);
    }

}
