package com.lab.controller;

import com.lab.service.StudentService;
import com.lab.vo.DirectionVO;
import com.lab.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/home")
@Tag(name = "首页相关接口", description = "提供首页展示数据、方向介绍等功能")
public class HomeController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/intro")
    @Operation(summary = "实验室介绍（空）")
    public ResultVO<String> intro() {
        return ResultVO.success("实验室介绍内容稍后补充");
    }

    @GetMapping("/direction-intro")
    @Operation(summary = "方向介绍")
    public ResultVO<List<DirectionVO>> directionIntro() {
        return ResultVO.success(Arrays.asList(
                new DirectionVO("嵌入式开发", "嵌入式开发主要是为特定硬件平台开发专用软件，通常用于微控制器、单片机、IoT设备等。"),
                new DirectionVO("后台开发", "后台开发主要负责服务器端的逻辑处理、数据库管理和业务功能的实现，是整个系统的“大脑”。"),
                new DirectionVO("前端开发", "前端开发主要关注用户界面的设计和实现，通过HTML、CSS、JavaScript等技术将视觉设计转化为交互体验。"),
                new DirectionVO("安卓开发", "安卓开发专注于为Android操作系统平台创建移动应用，覆盖智能手机、平板等设备。")
        ));
    }

    @GetMapping("/awards")
    @Operation(summary = "奖项介绍")
    public ResultVO<List<String>> awards() {
        return ResultVO.success(Arrays.asList(
                "全国仿真创新设计大赛国一",
                "全国大学生计算机设计大赛西北赛区二等奖",
                "数学建模大赛校赛一等奖",
                "互联网➕校赛二等奖",
                "24年TI杯电子设计竞赛省一",
                "蓝桥杯C/C++省一",
                "嵌入式系统专题邀请赛（英特尔杯）三等奖"
        ));
    }

    @GetMapping("/direction-data")
    @Operation(summary = "方向数据")
    public ResultVO<List<DirectionVO>> directionData() {
        return ResultVO.success(Arrays.asList(
                new DirectionVO("嵌入式", "基础知识：学习嵌入式系统原理、C/C++、单片机、RTOS。\n学习硬件：传感器、嵌入式电路设计。\n项目实践：智能家居、IoT 设备等。"),
                new DirectionVO("后台", "编程语言：Java、Python、Node.js。\n数据库：MySQL、MongoDB。\n框架：Spring、Django。\n架构：微服务、RESTful API、安全与性能优化。"),
                new DirectionVO("前端", "基础：HTML/CSS/JavaScript。\n框架：React、Vue。\n响应式、Git、跨平台：Flutter、React Native。"),
                new DirectionVO("安卓", "语言：Java/Kotlin。\nSDK：Android Studio、布局设计、SQLite、Room、发布与优化。")
        ));
    }




}
