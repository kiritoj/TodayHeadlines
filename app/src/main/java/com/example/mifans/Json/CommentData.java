package com.example.mifans.Json;

import java.util.List;

public class CommentData {

    /**
     * message : success
     * data : {"has_more":true,"total":1729,"comments":[{"text":"中国是一个负责任的大国，只要美方提供证据证明这东西是美国的，中国可以归还。流程是:美方提供生产厂家原始记录、生产编号，该型号潜航器的设计图纸，以及该次海洋调查任务的详细说明、行动计划等等。中方需要半年左右的调查就可以了。","digg_count":4834,"reply_data":{"reply_list":[]},"reply_count":194,"create_time":1481964505,"user":{"avatar_url":"https://p3.pstatp.com/thumb/da8f000ed9ffcfd72846","user_id":6180186929,"name":"hanxinping1969"},"dongtai_id":7105200091,"user_digg":0,"id":52960355361},{"text":"这位大哥！我对你是满满的佩服！第一，你不用骂人用讲理的；第二，你是用英语的。","digg_count":3466,"reply_data":{"reply_list":[]},"reply_count":30,"create_time":1481965439,"user":{"avatar_url":"https://p3.pstatp.com/thumb/65840005cc82277d58f2","user_id":5498858686,"name":"40大人"},"dongtai_id":7105344390,"user_digg":0,"id":52960416595},{"text":"在我家门口拍我家老婆冲凉?我会开枪的。(这是美国人自拥有枪枝的做法)拥有枪枝自卫的美国人很懂。在中国南海不断用武力对抗中国的美国大型舰队难道理解不出?中国的枪是防狼的?防入侵的?防战爭的?美国军事力量胆敢挑战中国领土，领海，领空是战爭入侵。必死。必败。必须国公审。必须培偿损失，必须让美国破产。","digg_count":24,"reply_data":{"reply_list":[]},"reply_count":5,"create_time":1482001668,"user":{"avatar_url":"https://p9.pstatp.com/thumb/a5d70006302b5f5bea3f","user_id":50063493249,"name":"元元125021907"},"dongtai_id":7110892364,"user_digg":0,"id":52978164774},{"text":"这次必须没收，千万不能送还美帝，最好还要就此事做点文章！","digg_count":74,"reply_data":{"reply_list":[]},"reply_count":0,"create_time":1481979641,"user":{"avatar_url":"https://p3.pstatp.com/thumb/9fd30011ff51fd9cc2d8","user_id":4519908420,"name":"中华1966"},"dongtai_id":7107686730,"user_digg":0,"id":52968678014},{"text":"美国人最善于用的是强盗逻辑，到人家家门口开车盗窃，人家发现并没收车辆，他却嚷嚷必须归还。这就是美国的嘴脸","digg_count":2819,"reply_data":{"reply_list":[]},"reply_count":13,"create_time":1481962949,"user":{"avatar_url":"https://p1.pstatp.com/thumb/1374/163864880","user_id":3087247280,"name":"安静的点"},"dongtai_id":7104944144,"user_digg":0,"id":52959642172},{"text":"这位老兄。身在虎中，不怕虎，单枪独斗。0K。","digg_count":22,"reply_data":{"reply_list":[]},"reply_count":0,"create_time":1481976805,"user":{"avatar_url":"https://p1.pstatp.com/thumb/3e7d0008d8c9ffda820c","user_id":6198148726,"name":"老杨82029081"},"dongtai_id":7107174531,"user_digg":0,"id":52966584361},{"text":"\u201c有什么事要帮忙吗？我们正在巡逻。\u201d回答得太好了！给中国海军赞一个！","digg_count":74,"reply_data":{"reply_list":[]},"reply_count":0,"create_time":1481976573,"user":{"avatar_url":"https://p3.pstatp.com/thumb/daea00001c72ae79e97a","user_id":25222880220,"name":"手机用户钱怀恩"},"dongtai_id":7107146018,"user_digg":0,"id":52966297603},{"text":"强大是最好的防御，中国的强大是敢对霸权主义采取措施！威武，大中国。","digg_count":15,"reply_data":{"reply_list":[]},"reply_count":0,"create_time":1481973900,"user":{"avatar_url":"https://p3.pstatp.com/thumb/97d0015ac8522d4c158","user_id":6052228236,"name":"吹剑一吷"},"dongtai_id":7106730859,"user_digg":0,"id":52965317513},{"text":"美国轰炸南联盟，炸我们的大使馆，我看一下，有几个人能记得？","digg_count":19,"reply_data":{"reply_list":[]},"reply_count":1,"create_time":1481971799,"user":{"avatar_url":"https://p3.pstatp.com/thumb/14f001ebceacbd0fbe8","user_id":5937645924,"name":"手机用户5937645924"},"dongtai_id":7106361227,"user_digg":0,"id":52963885228},{"text":"我反而觉得美国佬的要求也正常。想想前一段时间:砸抢运钞车，追死小偷，老太太偷别人的东西吃死小孩，北京老虎。。。。。。事件中国人的所作所为。也不觉得美国佬过分。","digg_count":1,"reply_data":{"reply_list":[]},"reply_count":1,"create_time":1482021085,"user":{"avatar_url":"https://p1.pstatp.com/thumb/1781000a4b42e1b6f40c","user_id":6084531226,"name":"二锅头78419519"},"dongtai_id":7112113208,"user_digg":0,"id":52981243465}]}
     */

    private String message;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * has_more : true
         * total : 1729
         * comments : [{"text":"中国是一个负责任的大国，只要美方提供证据证明这东西是美国的，中国可以归还。流程是:美方提供生产厂家原始记录、生产编号，该型号潜航器的设计图纸，以及该次海洋调查任务的详细说明、行动计划等等。中方需要半年左右的调查就可以了。","digg_count":4834,"reply_data":{"reply_list":[]},"reply_count":194,"create_time":1481964505,"user":{"avatar_url":"https://p3.pstatp.com/thumb/da8f000ed9ffcfd72846","user_id":6180186929,"name":"hanxinping1969"},"dongtai_id":7105200091,"user_digg":0,"id":52960355361},{"text":"这位大哥！我对你是满满的佩服！第一，你不用骂人用讲理的；第二，你是用英语的。","digg_count":3466,"reply_data":{"reply_list":[]},"reply_count":30,"create_time":1481965439,"user":{"avatar_url":"https://p3.pstatp.com/thumb/65840005cc82277d58f2","user_id":5498858686,"name":"40大人"},"dongtai_id":7105344390,"user_digg":0,"id":52960416595},{"text":"在我家门口拍我家老婆冲凉?我会开枪的。(这是美国人自拥有枪枝的做法)拥有枪枝自卫的美国人很懂。在中国南海不断用武力对抗中国的美国大型舰队难道理解不出?中国的枪是防狼的?防入侵的?防战爭的?美国军事力量胆敢挑战中国领土，领海，领空是战爭入侵。必死。必败。必须国公审。必须培偿损失，必须让美国破产。","digg_count":24,"reply_data":{"reply_list":[]},"reply_count":5,"create_time":1482001668,"user":{"avatar_url":"https://p9.pstatp.com/thumb/a5d70006302b5f5bea3f","user_id":50063493249,"name":"元元125021907"},"dongtai_id":7110892364,"user_digg":0,"id":52978164774},{"text":"这次必须没收，千万不能送还美帝，最好还要就此事做点文章！","digg_count":74,"reply_data":{"reply_list":[]},"reply_count":0,"create_time":1481979641,"user":{"avatar_url":"https://p3.pstatp.com/thumb/9fd30011ff51fd9cc2d8","user_id":4519908420,"name":"中华1966"},"dongtai_id":7107686730,"user_digg":0,"id":52968678014},{"text":"美国人最善于用的是强盗逻辑，到人家家门口开车盗窃，人家发现并没收车辆，他却嚷嚷必须归还。这就是美国的嘴脸","digg_count":2819,"reply_data":{"reply_list":[]},"reply_count":13,"create_time":1481962949,"user":{"avatar_url":"https://p1.pstatp.com/thumb/1374/163864880","user_id":3087247280,"name":"安静的点"},"dongtai_id":7104944144,"user_digg":0,"id":52959642172},{"text":"这位老兄。身在虎中，不怕虎，单枪独斗。0K。","digg_count":22,"reply_data":{"reply_list":[]},"reply_count":0,"create_time":1481976805,"user":{"avatar_url":"https://p1.pstatp.com/thumb/3e7d0008d8c9ffda820c","user_id":6198148726,"name":"老杨82029081"},"dongtai_id":7107174531,"user_digg":0,"id":52966584361},{"text":"\u201c有什么事要帮忙吗？我们正在巡逻。\u201d回答得太好了！给中国海军赞一个！","digg_count":74,"reply_data":{"reply_list":[]},"reply_count":0,"create_time":1481976573,"user":{"avatar_url":"https://p3.pstatp.com/thumb/daea00001c72ae79e97a","user_id":25222880220,"name":"手机用户钱怀恩"},"dongtai_id":7107146018,"user_digg":0,"id":52966297603},{"text":"强大是最好的防御，中国的强大是敢对霸权主义采取措施！威武，大中国。","digg_count":15,"reply_data":{"reply_list":[]},"reply_count":0,"create_time":1481973900,"user":{"avatar_url":"https://p3.pstatp.com/thumb/97d0015ac8522d4c158","user_id":6052228236,"name":"吹剑一吷"},"dongtai_id":7106730859,"user_digg":0,"id":52965317513},{"text":"美国轰炸南联盟，炸我们的大使馆，我看一下，有几个人能记得？","digg_count":19,"reply_data":{"reply_list":[]},"reply_count":1,"create_time":1481971799,"user":{"avatar_url":"https://p3.pstatp.com/thumb/14f001ebceacbd0fbe8","user_id":5937645924,"name":"手机用户5937645924"},"dongtai_id":7106361227,"user_digg":0,"id":52963885228},{"text":"我反而觉得美国佬的要求也正常。想想前一段时间:砸抢运钞车，追死小偷，老太太偷别人的东西吃死小孩，北京老虎。。。。。。事件中国人的所作所为。也不觉得美国佬过分。","digg_count":1,"reply_data":{"reply_list":[]},"reply_count":1,"create_time":1482021085,"user":{"avatar_url":"https://p1.pstatp.com/thumb/1781000a4b42e1b6f40c","user_id":6084531226,"name":"二锅头78419519"},"dongtai_id":7112113208,"user_digg":0,"id":52981243465}]
         */

        private boolean has_more;
        private int total;
        private List<CommentsBean> comments;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            /**
             * text : 中国是一个负责任的大国，只要美方提供证据证明这东西是美国的，中国可以归还。流程是:美方提供生产厂家原始记录、生产编号，该型号潜航器的设计图纸，以及该次海洋调查任务的详细说明、行动计划等等。中方需要半年左右的调查就可以了。
             * digg_count : 4834
             * reply_data : {"reply_list":[]}
             * reply_count : 194
             * create_time : 1481964505
             * user : {"avatar_url":"https://p3.pstatp.com/thumb/da8f000ed9ffcfd72846","user_id":6180186929,"name":"hanxinping1969"}
             * dongtai_id : 7105200091
             * user_digg : 0
             * id : 52960355361
             */

            private String text;
            private int digg_count;
            private ReplyDataBean reply_data;
            private int reply_count;
            private int create_time;
            private UserBean user;
            private long dongtai_id;
            private int user_digg;
            private long id;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getDigg_count() {
                return digg_count;
            }

            public void setDigg_count(int digg_count) {
                this.digg_count = digg_count;
            }

            public ReplyDataBean getReply_data() {
                return reply_data;
            }

            public void setReply_data(ReplyDataBean reply_data) {
                this.reply_data = reply_data;
            }

            public int getReply_count() {
                return reply_count;
            }

            public void setReply_count(int reply_count) {
                this.reply_count = reply_count;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public long getDongtai_id() {
                return dongtai_id;
            }

            public void setDongtai_id(long dongtai_id) {
                this.dongtai_id = dongtai_id;
            }

            public int getUser_digg() {
                return user_digg;
            }

            public void setUser_digg(int user_digg) {
                this.user_digg = user_digg;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public static class ReplyDataBean {
                private List<?> reply_list;

                public List<?> getReply_list() {
                    return reply_list;
                }

                public void setReply_list(List<?> reply_list) {
                    this.reply_list = reply_list;
                }
            }

            public static class UserBean {
                /**
                 * avatar_url : https://p3.pstatp.com/thumb/da8f000ed9ffcfd72846
                 * user_id : 6180186929
                 * name : hanxinping1969
                 */

                private String avatar_url;
                private long user_id;
                private String name;

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public long getUser_id() {
                    return user_id;
                }

                public void setUser_id(long user_id) {
                    this.user_id = user_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
