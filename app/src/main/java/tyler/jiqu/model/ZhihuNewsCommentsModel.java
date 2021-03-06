package tyler.jiqu.model;

import java.util.List;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/9  14:08.
 * @描述 ${TODO}.
 */
public class ZhihuNewsCommentsModel {

    /**
     * author : 每一天都在混水摸鱼
     * content : 钱会让它变的好吃
     * avatar : http://pic3.zhimg.com/0ecf2216c2612b04592126adc16affa2_im.jpg
     * time : 1413987020
     * id : 556780
     * likes : 0
     */

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        private String author;
        private String content;
        private String avatar;
        private long time;
        private int id;
        private int likes;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public long getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        @Override
        public String toString() {
            return "CommentsBean{" +
                    "author='" + author + '\'' +
                    ", content='" + content + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", time=" + time +
                    ", id=" + id +
                    ", likes=" + likes +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ZhihuNewsCommentsModel{" +
                "comments=" + comments +
                '}';
    }
}
