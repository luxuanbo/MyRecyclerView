package recycler.xhf.asus.myrecyclerview.bean;

import java.util.List;

/**
 * Created by asus on 2016/10/24.
 */
public class Data {

    public DataEntity data;
    public String info;
    public String ra_referer;
    public int status;
    public int times;

    public static class DataEntity {

        public CountsEntity counts;
        public List<ForumListEntity> forum_list;

        public static class CountsEntity {

            public int ask;
            public int company;
        }

        public static class ForumListEntity {


            public int id;
            public String name;
            public List<GroupEntity> group;

            public static class GroupEntity {

                public int id;
                public String name;
                public String photo;
                public String total_threads;
                public List<TypesEntity> types;

                public boolean hasTitle=false;
                public String title=null;

                public void setHasTitle(boolean hasTitle,String title) {
                    this.hasTitle = hasTitle;
                    this.title=title;
                }

                public static class TypesEntity {

                    public int id;
                    public String name;
                }
            }
        }
    }
}
