package club.sdll.ptc.mybatis.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author chengxiwang
 */
@Data
@EqualsAndHashCode
public class BlogNote {
    private String id;

    private String title;

    private String secondTitle;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String type;

    private String blogContent;

    private int blogEye;//浏览次数

    private int blogStar;//收藏次数

    private int blogThumbs;//点赞次数

    private int blogDownload;//下载次数

    private int blogComment;//评论次数

    private BlogExt ext1;



}