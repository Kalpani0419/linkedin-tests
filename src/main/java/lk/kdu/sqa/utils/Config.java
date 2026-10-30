package lk.kdu.sqa.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (FileInputStream in = new FileInputStream("config.properties")) {
            props.load(in);
        } catch (Exception ignored) { }
    }

    // Environment variable wins, then config.properties
    public static String get(String envKey, String propKey) {
        String v = System.getenv(envKey);
        return (v != null && !v.isBlank()) ? v : props.getProperty(propKey);
    }

    public static String username() { return get("LINKEDIN_USER", "linkedin.username"); }
    public static String password() { return get("LINKEDIN_PASS", "linkedin.password"); }
}