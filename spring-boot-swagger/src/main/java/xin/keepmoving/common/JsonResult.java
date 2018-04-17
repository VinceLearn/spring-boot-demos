package xin.keepmoving.common;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018-04-08 9:48
 */
public class JsonResult {
    public static final String OK = "ok";
    public static final String FAIL = "fail";

    private String status = null;
    private Object result = null;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setOK() {
        status = JsonResult.OK;
    }

    public void setFail() {
        status = JsonResult.FAIL;
    }
}
