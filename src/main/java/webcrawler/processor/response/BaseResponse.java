package webcrawler.processor.response;

public  abstract class BaseResponse {

    public int id ;
    public String timeStamp;
    public String secondsTaken;
    public String pagesExplored;
    public String searchDepth;

    public BaseResponse(int id , String timeStamp , String secondsTaken , String pagesExplored , String searchDepth) {
        this.id = id ;
        this.timeStamp = timeStamp;
        this.secondsTaken = secondsTaken;
        this.searchDepth = searchDepth;
        this.pagesExplored = pagesExplored;
    }

}
