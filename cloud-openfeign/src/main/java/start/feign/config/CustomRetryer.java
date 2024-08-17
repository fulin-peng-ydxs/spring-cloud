package start.feign.config;


import feign.RetryableException;
import feign.Retryer;

/** 自定义重试器
 * @author pengshuaifeng
 * 2024/8/17
 */
public class CustomRetryer implements Retryer {
    private final int maxAttempts;  //最大重试次数
    private final long period;  //初始间隔时间
    private final long maxPeriod;   //最大间隔时间
    private int attempt;  //当前重试次数

    public CustomRetryer() {
        this(100, 1000, 3);
    }

    public CustomRetryer(long period, long maxPeriod, int maxAttempts) {
        this.period = period;
        this.maxPeriod = maxPeriod;
        this.maxAttempts = maxAttempts;
        this.attempt = 1;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts) {
            throw e;
        }
        long interval;
        if (e.retryAfter() != null) {
            interval = e.retryAfter().getTime() - System.currentTimeMillis();
            if (interval > maxPeriod) {
                interval = maxPeriod;
            }
            if (interval < 0) {
                return;
            }
        } else {
            interval = nextMaxInterval();
        }
        try {
            Thread.sleep(interval);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }

    long nextMaxInterval() {
        long interval = (long) (period * Math.pow(1.5, attempt - 1));
        return interval > maxPeriod ? maxPeriod : interval;
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer(period, maxPeriod, maxAttempts);
    }
}
