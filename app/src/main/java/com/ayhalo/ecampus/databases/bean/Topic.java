package com.ayhalo.ecampus.databases.bean;

import java.util.List;

/**
 * com.ayhalo.ecampus.databases.bean
 * create by wuyh 2018/4/12.
 */

public class Topic {


    /**
     * error : false
     * results : [{"article_url":"http://fzxy.sqc.edu.cn/article-detail-984039.html","belong":"fzxy","create_time":"2018-04-12 23:38:17","id":9813,"isnotice":false,"publish_time":"2018-04-12","title":"马克思主义学院集中学习校马克思主义学院建设推进会精神"},{"article_url":"http://fzxy.sqc.edu.cn/article-detail-983994.html","belong":"fzxy","create_time":"2018-04-12 23:38:17","id":9814,"isnotice":false,"publish_time":"2018-04-03","title":"马克思主义学院建设推进会召开"},{"article_url":"http://fzxy.sqc.edu.cn/article-detail-983981.html","belong":"fzxy","create_time":"2018-04-12 23:38:18","id":9815,"isnotice":false,"publish_time":"2018-04-02","title":"宿迁学院中研会召开全体骨干成员大会"},{"article_url":"http://fzxy.sqc.edu.cn/article-detail-983978.html","belong":"fzxy","create_time":"2018-04-12 23:38:18","id":9816,"isnotice":false,"publish_time":"2018-04-02","title":"法政学院联合市妇联创办\u201c大爱宿迁\u2022巷里阳光\u201d公益工作坊"},{"article_url":"http://fzxy.sqc.edu.cn/article-detail-983964.html","belong":"fzxy","create_time":"2018-04-12 23:38:19","id":9817,"isnotice":false,"publish_time":"2018-03-30","title":"法政学院开展\u201c书记面对面\u201d活动"},{"article_url":"http://bs.sqc.edu.cn/article-detail-983879.html","belong":"bs","create_time":"2018-04-12 23:35:21","id":9595,"isnotice":false,"publish_time":"2018-03-20","title":"商学院3·15春风社走进小学义讲活动受到《宿迁晚报》头版报道"},{"article_url":"http://bs.sqc.edu.cn/article-detail-983858.html","belong":"bs","create_time":"2018-04-12 23:35:22","id":9596,"isnotice":false,"publish_time":"2018-03-19","title":"商学院团总支楚暖志愿队开展空巢老人暖春活动"},{"article_url":"http://bs.sqc.edu.cn/article-detail-983856.html","belong":"bs","create_time":"2018-04-12 23:35:22","id":9597,"isnotice":false,"publish_time":"2018-03-19","title":"商学院17级英语四级考试再创佳绩"},{"article_url":"http://bs.sqc.edu.cn/article-detail-983850.html","belong":"bs","create_time":"2018-04-12 23:35:23","id":9598,"isnotice":false,"publish_time":"2018-03-17","title":"顺丰速运集团苏北区代表至商学院洽谈校企合作事宜"},{"article_url":"http://fzxy.sqc.edu.cn/article-detail-983832.html","belong":"fzxy","create_time":"2018-04-12 23:38:19","id":9818,"isnotice":false,"publish_time":"2018-03-15","title":"法政学院召开\u201c两评估一认证\u201d学习会"}]
     */

    private String error;
    private List<ResultsBean> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * article_url : http://fzxy.sqc.edu.cn/article-detail-984039.html
         * belong : fzxy
         * create_time : 2018-04-12 23:38:17
         * id : 9813
         * isnotice : false
         * publish_time : 2018-04-12
         * title : 马克思主义学院集中学习校马克思主义学院建设推进会精神
         */

        private String article_url;
        private String belong;
        private String create_time;
        private int id;
        private boolean isnotice;
        private String publish_time;
        private String title;

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public String getBelong() {
            return belong;
        }

        public void setBelong(String belong) {
            this.belong = belong;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsnotice() {
            return isnotice;
        }

        public void setIsnotice(boolean isnotice) {
            this.isnotice = isnotice;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
