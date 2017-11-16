public class NetComponent {
    private String realIp;
    private String fakeIp;
    private Integer port;


    @Override
    public String toString() {
        return realIp + ',' + fakeIp + ',' + port;
    }

    public NetComponent(String[] data, String connectionIP) {
        //NodeIP, myIP (dispatcher), 7 (action type), nodePort
        this.realIp = connectionIP;
        this.fakeIp = data[0];
        String aux = data[3];
        aux = aux.trim();
        this.port = Integer.parseInt(aux);
        System.out.println("dataTable entry created: " + realIp + ", " + fakeIp + ", " + port);


        /*//realIP (1) + fakeIP (2) + port (3)
        this.realIp = data[1];
        this.fakeIp = data[2];
        this.port = Integer.parseInt(data[3]);
        */
    }

    public NetComponent(String realIp, String fakeIP, int port){
        this.realIp = realIp;
        this.fakeIp = fakeIP;
        this.port = port;

    }


    public String getRealIp() {
        return realIp;
    }

    public void setRealIp(String realIp) {
        this.realIp = realIp;
    }

    public String getFakeIp() {
        return fakeIp;
    }

    public void setFakeIp(String fakeIp) {
        this.fakeIp = fakeIp;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


}

