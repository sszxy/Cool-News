package com.example.xinwin;

import java.util.List;

/**
 * Created by 张翔宇 on 2018/4/8.
 */

public class news {
    /**
     * status : 0
     * msg : ok
     * result : {"channel":"头条","num":"1","list":[{"title":"日本\u201c第四自卫队\u201d呼之欲出 安倍野心又升级","time":"2018-04-08 09:35:12","src":"","category":"news","pic":"http://api.jisuapi.com/news/upload/201804/08110007_73784.jpg","content":" 原标题：\u201c第四自卫队\u201d呼之欲出 安倍野心又升级<\/p> 据媒体报道，上周日本自卫队成立了\u201c陆上总队\u201d，这使陆上自卫队在海外的行动能力得到强化，与海、空自卫队以及美军的协调实现一体化。为此，日本国会两院3月28日通过了2018财年预算，总额超过约97.71万亿日元。防务预算为5.19万亿日元，创下历史记录，其中部分为\u201c陆上总队\u201d总部建设费，相关工程于今年动工。而为满足安倍政府促进日美安保同盟关系，\u201c陆上总队\u201d司令部特意选在离驻日美军总部驻地不远的东京朝霞基地，编制规模为300人左右，目前先维持180人。\u201c陆上总队\u201d，将作为五个方面队北部、东北、东部、中部和西部的上级，还会成立\u201c日美共同部\u201d，与驻日美国陆军协调。目前，5个方面队，共有15个师团、旅团，含现役人员15.1万人，预备役人员0.8万人，同时也将直接指挥新设的\u201c水陆机动团\u201d以及第一空挺团等精锐部队，全面强化西南岛屿防卫的作战能力。\u201c陆上总队\u201d与海上自卫队的舰队和航空自卫队的总队平级，统一由防卫大臣调用。那么日本自卫队军改主要目的是什么？<\/p> 除了建立\u201c陆上总队\u201d，第二项就是建立负责岛屿防卫和登陆作战的\u201c水陆机动团\u201d。以2002年成立的陆上自卫队\u201c西部方面普通科连队\u201d为基础，而这个所谓的\u201c西普连\u201d正是针对我国钓鱼岛而成立的，拜美国海军陆战队为师，搞联合训练，夺岛强攻、侦察渗透、潜水训练等等特种作战。\u201c水陆机动团\u201d司令部设在九州地区的长崎县佐世保市相浦基地。不过目前，计划装备给\u201c水陆机动团\u201d的美军AAV-7水陆两栖战车推迟交付，V-22\u201c鱼鹰\u201d旋翼运输机故障频出，该部队的装备一时间尚无法全部到位。日本除了建新军，还为自卫队加强通信能力。据日本防卫省3月20日消息，为提高自卫队的情报通信，强化西南诸岛军事联络，要建立军用卫星通信网。将于下个月在南美法属圭亚那发射通信卫星\u201c煌-1\u201d号。\u201c煌-2\u201d号于去年1月发射升空并运用。那么为何日本不断加强针对西南军事力量？<\/p> 3月29日，日本安全保障法相关内容施行两周年。这两年，日本大幅度地扩大了日本自卫队可实施的任务。安倍上台后，日本战略重心由北转南，加强九州、冲绳地区军事实力，据《朝日新闻》报道，日本将向冲绳本岛部署\u201c12式\u201d地对舰导弹。作为日本\u201c88式\u201d反舰导弹的后继装备，全长5米，重约700公斤，最大射程200公里，是日本最新研制的反舰导弹。此举意在从宫古海峡两侧对通过宫古海峡的中国海军舰艇形成\u201c夹击\u201d，从而对宫古海峡形成火力全覆盖。日本的防务预算，以\u201c安保环境严峻\u201d为由持续增长理由，安倍政府决定列入22亿日元用于引进秉持专守防卫的巡航导弹，采购对象可能是挪威生产的射程500公里的联合打击导弹，包括美国产的射程900公里的反舰导弹和联合防区外空对地导弹。对于日本将要购置导弹一说，日本防卫大臣做出这样解释，\u201c为了击退无法抵达日本本土的所谓\u201c圈外威胁\u201d的敌方攻击，我们自己必须拥有远程导弹，\u201d而媒体认为第二种导弹用途明显是针对中国，而对西南诸岛采取的军事措施。安倍的野心是否又升级了？<\/p>","url":"http://news.sina.cn/gj/2018-04-08/detail-ifyvtmxc7560206.d.html?vt=4&pos=108","weburl":"http://news.sina.com.cn/w/2018-04-08/doc-ifyvtmxc7560206.shtml"}]}
     */

    private String status;
    private String msg;
    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * channel : 头条
         * num : 1
         * list : [{"title":"日本\u201c第四自卫队\u201d呼之欲出 安倍野心又升级","time":"2018-04-08 09:35:12","src":"","category":"news","pic":"http://api.jisuapi.com/news/upload/201804/08110007_73784.jpg","content":" 原标题：\u201c第四自卫队\u201d呼之欲出 安倍野心又升级<\/p> 据媒体报道，上周日本自卫队成立了\u201c陆上总队\u201d，这使陆上自卫队在海外的行动能力得到强化，与海、空自卫队以及美军的协调实现一体化。为此，日本国会两院3月28日通过了2018财年预算，总额超过约97.71万亿日元。防务预算为5.19万亿日元，创下历史记录，其中部分为\u201c陆上总队\u201d总部建设费，相关工程于今年动工。而为满足安倍政府促进日美安保同盟关系，\u201c陆上总队\u201d司令部特意选在离驻日美军总部驻地不远的东京朝霞基地，编制规模为300人左右，目前先维持180人。\u201c陆上总队\u201d，将作为五个方面队北部、东北、东部、中部和西部的上级，还会成立\u201c日美共同部\u201d，与驻日美国陆军协调。目前，5个方面队，共有15个师团、旅团，含现役人员15.1万人，预备役人员0.8万人，同时也将直接指挥新设的\u201c水陆机动团\u201d以及第一空挺团等精锐部队，全面强化西南岛屿防卫的作战能力。\u201c陆上总队\u201d与海上自卫队的舰队和航空自卫队的总队平级，统一由防卫大臣调用。那么日本自卫队军改主要目的是什么？<\/p> 除了建立\u201c陆上总队\u201d，第二项就是建立负责岛屿防卫和登陆作战的\u201c水陆机动团\u201d。以2002年成立的陆上自卫队\u201c西部方面普通科连队\u201d为基础，而这个所谓的\u201c西普连\u201d正是针对我国钓鱼岛而成立的，拜美国海军陆战队为师，搞联合训练，夺岛强攻、侦察渗透、潜水训练等等特种作战。\u201c水陆机动团\u201d司令部设在九州地区的长崎县佐世保市相浦基地。不过目前，计划装备给\u201c水陆机动团\u201d的美军AAV-7水陆两栖战车推迟交付，V-22\u201c鱼鹰\u201d旋翼运输机故障频出，该部队的装备一时间尚无法全部到位。日本除了建新军，还为自卫队加强通信能力。据日本防卫省3月20日消息，为提高自卫队的情报通信，强化西南诸岛军事联络，要建立军用卫星通信网。将于下个月在南美法属圭亚那发射通信卫星\u201c煌-1\u201d号。\u201c煌-2\u201d号于去年1月发射升空并运用。那么为何日本不断加强针对西南军事力量？<\/p> 3月29日，日本安全保障法相关内容施行两周年。这两年，日本大幅度地扩大了日本自卫队可实施的任务。安倍上台后，日本战略重心由北转南，加强九州、冲绳地区军事实力，据《朝日新闻》报道，日本将向冲绳本岛部署\u201c12式\u201d地对舰导弹。作为日本\u201c88式\u201d反舰导弹的后继装备，全长5米，重约700公斤，最大射程200公里，是日本最新研制的反舰导弹。此举意在从宫古海峡两侧对通过宫古海峡的中国海军舰艇形成\u201c夹击\u201d，从而对宫古海峡形成火力全覆盖。日本的防务预算，以\u201c安保环境严峻\u201d为由持续增长理由，安倍政府决定列入22亿日元用于引进秉持专守防卫的巡航导弹，采购对象可能是挪威生产的射程500公里的联合打击导弹，包括美国产的射程900公里的反舰导弹和联合防区外空对地导弹。对于日本将要购置导弹一说，日本防卫大臣做出这样解释，\u201c为了击退无法抵达日本本土的所谓\u201c圈外威胁\u201d的敌方攻击，我们自己必须拥有远程导弹，\u201d而媒体认为第二种导弹用途明显是针对中国，而对西南诸岛采取的军事措施。安倍的野心是否又升级了？<\/p>","url":"http://news.sina.cn/gj/2018-04-08/detail-ifyvtmxc7560206.d.html?vt=4&pos=108","weburl":"http://news.sina.com.cn/w/2018-04-08/doc-ifyvtmxc7560206.shtml"}]
         */

        private String channel;
        private String num;
        private List<ListBean> list;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * title : 日本“第四自卫队”呼之欲出 安倍野心又升级
             * time : 2018-04-08 09:35:12
             * src :
             * category : news
             * pic : http://api.jisuapi.com/news/upload/201804/08110007_73784.jpg
             * content :  原标题：“第四自卫队”呼之欲出 安倍野心又升级</p> 据媒体报道，上周日本自卫队成立了“陆上总队”，这使陆上自卫队在海外的行动能力得到强化，与海、空自卫队以及美军的协调实现一体化。为此，日本国会两院3月28日通过了2018财年预算，总额超过约97.71万亿日元。防务预算为5.19万亿日元，创下历史记录，其中部分为“陆上总队”总部建设费，相关工程于今年动工。而为满足安倍政府促进日美安保同盟关系，“陆上总队”司令部特意选在离驻日美军总部驻地不远的东京朝霞基地，编制规模为300人左右，目前先维持180人。“陆上总队”，将作为五个方面队北部、东北、东部、中部和西部的上级，还会成立“日美共同部”，与驻日美国陆军协调。目前，5个方面队，共有15个师团、旅团，含现役人员15.1万人，预备役人员0.8万人，同时也将直接指挥新设的“水陆机动团”以及第一空挺团等精锐部队，全面强化西南岛屿防卫的作战能力。“陆上总队”与海上自卫队的舰队和航空自卫队的总队平级，统一由防卫大臣调用。那么日本自卫队军改主要目的是什么？</p> 除了建立“陆上总队”，第二项就是建立负责岛屿防卫和登陆作战的“水陆机动团”。以2002年成立的陆上自卫队“西部方面普通科连队”为基础，而这个所谓的“西普连”正是针对我国钓鱼岛而成立的，拜美国海军陆战队为师，搞联合训练，夺岛强攻、侦察渗透、潜水训练等等特种作战。“水陆机动团”司令部设在九州地区的长崎县佐世保市相浦基地。不过目前，计划装备给“水陆机动团”的美军AAV-7水陆两栖战车推迟交付，V-22“鱼鹰”旋翼运输机故障频出，该部队的装备一时间尚无法全部到位。日本除了建新军，还为自卫队加强通信能力。据日本防卫省3月20日消息，为提高自卫队的情报通信，强化西南诸岛军事联络，要建立军用卫星通信网。将于下个月在南美法属圭亚那发射通信卫星“煌-1”号。“煌-2”号于去年1月发射升空并运用。那么为何日本不断加强针对西南军事力量？</p> 3月29日，日本安全保障法相关内容施行两周年。这两年，日本大幅度地扩大了日本自卫队可实施的任务。安倍上台后，日本战略重心由北转南，加强九州、冲绳地区军事实力，据《朝日新闻》报道，日本将向冲绳本岛部署“12式”地对舰导弹。作为日本“88式”反舰导弹的后继装备，全长5米，重约700公斤，最大射程200公里，是日本最新研制的反舰导弹。此举意在从宫古海峡两侧对通过宫古海峡的中国海军舰艇形成“夹击”，从而对宫古海峡形成火力全覆盖。日本的防务预算，以“安保环境严峻”为由持续增长理由，安倍政府决定列入22亿日元用于引进秉持专守防卫的巡航导弹，采购对象可能是挪威生产的射程500公里的联合打击导弹，包括美国产的射程900公里的反舰导弹和联合防区外空对地导弹。对于日本将要购置导弹一说，日本防卫大臣做出这样解释，“为了击退无法抵达日本本土的所谓“圈外威胁”的敌方攻击，我们自己必须拥有远程导弹，”而媒体认为第二种导弹用途明显是针对中国，而对西南诸岛采取的军事措施。安倍的野心是否又升级了？</p>
             * url : http://news.sina.cn/gj/2018-04-08/detail-ifyvtmxc7560206.d.html?vt=4&pos=108
             * weburl : http://news.sina.com.cn/w/2018-04-08/doc-ifyvtmxc7560206.shtml
             */

            private String title;
            private String time;
            private String src;
            private String category;
            private String pic;
            private String content;
            private String url;
            private String weburl;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getWeburl() {
                return weburl;
            }

            public void setWeburl(String weburl) {
                this.weburl = weburl;
            }
        }
    }
}
