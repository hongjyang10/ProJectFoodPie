package com.lanou.dllo.myfoodpie.potting;

/*
         |              |
         | \            | \
         |   | | | | | |    | | | | |||||\
         |                          |||||||\
         |         ( )              ||||||||
         |                           |||||/
         |                  | | | | | |||/
         |    |             |          |
         |    |             |          |
       / |   | |            |          |\
      |      |/             |          \|
       \ |                  |
         |                  |
           \ | | | | | | | /
             |       |            <-----弱鸡
             |       |
             |       |
*/
public class NetTool implements NetInterface {
    private static NetTool netTool;
    private NetInterface netInterface;

    public static NetTool getInstance() {
        if (netTool == null) {
            synchronized (NetTool.class) {
                if (netTool == null) {
                    netTool = new NetTool();
                }
            }
        }
        return netTool;
    }

    private NetTool() {
        netInterface = new OkTool();
    }

    @Override
    public void startRequest(String url, CallBack<String> callBack) {
        netInterface.startRequest(url, callBack);
    }

    @Override
    public <T> void startRequest(String url, Class<T> tClass, CallBack<T> callback) {
        netInterface.startRequest(url, tClass, callback);
    }
}
