package xin.keepmoving.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xin.keepmoving.common.JsonResult;
import xin.keepmoving.domain.Role;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018-04-17 10:18
 */
@Api(value = "roleapi", tags = {"role", "用户"})
@RestController
@RequestMapping("/api/role")
public class RoleApiController {
    static Map<Long, Role> roles = Collections.synchronizedMap(new HashMap<Long, Role>());

    @ApiOperation(value = "获取角色详细信息", notes = "根据用户的id获取角色详细信息")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/getRole/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getRole(@PathVariable() Long id) {
        Role role = roles.get(id);
        JsonResult r = new JsonResult();
        r.setResult(role);
        r.setStatus(JsonResult.OK);

        return ResponseEntity.ok(r);
    }
}
