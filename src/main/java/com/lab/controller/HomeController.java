package com.lab.controller;

import com.lab.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/home")
@Tag(name = "首页相关接口", description = "提供首页展示数据、方向介绍等功能")
public class HomeController {

    @GetMapping("/intro")
    @Operation(summary = "实验室介绍（空）")
    public ResultVO<String> intro() {
        return ResultVO.success("实验室介绍内容稍后补充");
    }

    @GetMapping("/awards")
    @Operation(summary = "奖项介绍")
    public ResultVO<String[]> awards() {
        return ResultVO.success(new String[]{
                "全国仿真创新设计大赛国一",
                "全国大学生计算机设计大赛西北赛区二等奖",
                "数学建模大赛校赛一等奖",
                "互联网＋校赛二等奖",
                "24年TI杯电子设计竞赛省一",
                "蓝桥杯C/C++省一",
                "嵌入式系统专题邀请赛（英特尔杯）三等奖"
        });
    }
}
