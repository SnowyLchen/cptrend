(function ($) {
    $.fn.onLine = function (options) {
        var box = this;
        var regainCanvas = options.regainCanvas;
        var linewidth = 1, linestyle = "#0C6";//连线绘制--线宽，线色
        var part1, part2;

        part1 = $(".character.choose span[isEvenNum=true]");
        part2 = $(".character.choose span[isEvenNum=false]");
        //初始化赋值 列表内容
        part1.each(function (index, element) {
            $(this).attr({
                group: "gpl",
                left: $(this).position().left + $(this).outerWidth(),
                top: $(this).position().top + $(this).outerHeight() / 2,
                sel: "0",
                check: "0"
            });
        });
        part2.each(function (index, element) {
            $(this).attr({
                group: "gpr",
                left: $(this).position().left,
                top: $(this).position().top + $(this).outerHeight() / 2,
                sel: "0",
                check: "0"
            });
        });
        part1.attr('first', 0);//初始赋值 列表内容容器
        part2.attr('first', 0);
        //canvas 赋值
        var canvas = box.find(".canvas")[0];  //获取canvas  实际连线标签
        canvas.width = box.find(".table").width();//canvas宽度等于div容器宽度
        canvas.height = box.find(".table").height();
        var backcanvas = box.find(".backcanvas")[0];  //获取canvas 模拟连线标签
        backcanvas.width = box.find(".table").width();
        backcanvas.height = box.find(".table").height();
        //连线数据
        var mx = [];//连线坐标
        var my = [];
        var ms = [];
        var pair = 0;//配对属性

        //canvas 追加2d画布
        var context = canvas.getContext('2d');  //canvas追加2d画图
        var lastX, lastY;//存放遍历坐标
        window.strockline = function () {//绘制方法
            context.clearRect(0, 0, box.find(".table").width(), box.find(".table").height());//整个画布清除
            context.save();
            context.beginPath();
            context.lineWidth = linewidth;
            for (var i = 0; i < dataNum.length; i++) {  //遍历绘制
                console.log(dataNum[i].moveX + '--move--' + dataNum[i].moveY)
                context.moveTo(dataNum[i].moveX, dataNum[i].moveY);
                context.lineTo(dataNum[i].toX, dataNum[i].toY);
                console.log(dataNum[i].toX + '--to--' + dataNum[i].toY)
            }
            context.strokeStyle = linestyle;
            context.stroke();
            context.restore();
        };

        function clearline() {//清除
            context.clearRect(0, 0, box.find(".show").width(), box.find(".show").height());
            mx = [];//数据清除
            my = [];
            ms = [];
            pairl = [];
            pairr = [];
            pair = 0;
            part1.children("span").each(function (index, element) {
                $(this).removeClass("addstyle");
                $(this).attr("sel", "0");
                $(this).attr("check", "0");

            });
            part1.attr('first', 0);
            part2.children("span").each(function (index, element) {
                $(this).removeClass("addstyle");
                $(this).attr("sel", "0");
                $(this).attr("check", "0");
            });
            part2.attr('first', 0);
        };

        //重置
        box.find(".resetCanvasBtn").click(function () {
            clearline();
        });

        // //获取连线题对应的key-value
        // function getListPair() {
        //     var index;
        //     var leftVal, rightVal, nowVal;
        //     var sum = pair * 2;
        //     var pairA = new Array(sum);
        //     var size = part1.find(".cycle").size();
        //     for (var i = 0; i < size; i++) {
        //         if (part1.find(".cycle").eq(i).hasClass("addstyle") == true) {
        //             nowVal = part1.find(".cycle").eq(i).attr("pair");
        //             leftVal = parseInt(nowVal) * 2;
        //             pairA[leftVal] = i;
        //         }
        //     }
        //     for (var i = 0; i < size; i++) {
        //         if (part2.find(".cycle").eq(i).hasClass("addstyle") == true) {
        //             nowVal = part2.find(".cycle").eq(i).attr("pair");
        //             rightVal = parseInt(nowVal) * 2 + 1;
        //             pairA[rightVal] = i;
        //         }
        //     }
        //
        //     var idStr;
        //     var idAttr = [];
        //     for (var i = 0; i < sum; i++) {
        //         if (typeof pairA[i] != "undefined") {
        //             if (i % 2 == 0) {
        //                 idStr = part1.find(".cycle").eq(pairA[i]).attr("data-id");
        //             } else {
        //                 idStr = idStr + " : " + part2.find(".cycle").eq(pairA[i]).attr("data-id");
        //                 idAttr.push(idStr);
        //                 idStr = null;
        //             }
        //         }
        //
        //     }
        //
        //     alert(idAttr);
        // }

        //生成连线题默认的答案
        function newCanvas() {
            if (regainCanvas == true) {
                strockline();
            }
        };
        // box.find(".getPair").click(function () {
        //     getListPair();
        // });
    }
})(jQuery);