package com.mj.event.core.job.jobhandler.nmzsks.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.mj.event.core.commen.EmailUtil;
import com.mj.event.core.core.entity.NmZsksZxks;
import com.mj.event.core.core.service.NmZsksZxksService;
import com.mj.event.core.job.jobhandler.nmzsks.utils.HttpsUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 内蒙自学考试监控任务
 */
@Component
public class NmZiXueKaoShiJob {

    @Resource
    private NmZsksZxksService nmZsksZxksService;

    private static Logger logger = LoggerFactory.getLogger(NmZiXueKaoShiJob.class);


    /**
     * 1、公告栏通知消息
     */
    @XxlJob("noticeBoard")
    public ReturnT<String> noticeBoardJobHandler(String param) throws Exception {
        HttpsUtil.trustEveryone();
        String url = "https://www.nm.zsks.cn/zxks/";
        Connection connect = Jsoup.connect(url);
        Element body = connect.get().body();
        Element noborder = body.getElementsByClass("NOBORDER").get(0);
        Element a = noborder.getElementsByTag("a").get(0);
        Element date = noborder.getElementsByTag("font").get(0);
        String title = a.text();
        DateTime dateTime = DateUtil.parse(date.text().substring(2, 12));
        String href = a.attr("href").replace("./", "https://www.nm.zsks.cn/zxks/");
        NmZsksZxks nmZsksZxks = new NmZsksZxks();
        nmZsksZxks.setId(1);
        nmZsksZxks.setName("自学考试 公告栏");
        nmZsksZxks.setTitle(title);
        nmZsksZxks.setDate(dateTime);
        nmZsksZxks.setHeartDate(new Date());
        nmZsksZxks.setContext(href);
        NmZsksZxks byId = nmZsksZxksService.getById(1);
        if (ObjectUtil.isEmpty(body)) {
            nmZsksZxksService.save(nmZsksZxks);
        } else {
            if (!byId.getTitle().equals(title)) {
                nmZsksZxksService.updateById(nmZsksZxks);
                /**
                 * 通知订阅该功能的人
                 */
            }
        }

        logger.info("测试任务执行成功！");
        return ReturnT.SUCCESS;
    }


    public static void main(String args[]) throws Exception {
        EmailUtil.sendEmailMeathon("1546004778@qq.com", "测试", "测试的发送内容");

    }
}
