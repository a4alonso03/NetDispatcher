public class RouterInfo {
    private String routerRealIp;
    private String routerFakeIp;
    private Integer routerPort;
    private boolean isTerminal;


    @Override
    public String toString() {
        return routerRealIp + '\n' + routerFakeIp + '\n' + routerPort;
    }

    public RouterInfo(String[] data, boolean isTerminal) {
        //type (0) + realIP (1) + fakeIP (2) + port (3)
        this.routerRealIp = data[1];
        this.routerFakeIp = data[2];
        this.routerPort = Integer.parseInt(data[3]);
    }

    public String getRouterRealIp() {
        return routerRealIp;
    }

    public void setRouterRealIp(String routerRealIp) {
        this.routerRealIp = routerRealIp;
    }

    public String getRouterFakeIp() {
        return routerFakeIp;
    }

    public void setRouterFakeIp(String routerFakeIp) {
        this.routerFakeIp = routerFakeIp;
    }

    public Integer getRouterPort() {
        return routerPort;
    }

    public void setRouterPort(Integer routerPort) {
        this.routerPort = routerPort;
    }
    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

}

