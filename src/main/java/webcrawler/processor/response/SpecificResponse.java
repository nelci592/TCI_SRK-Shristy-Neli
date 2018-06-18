package webcrawler.processor.response;

import webcrawler.model.BaseModel;

public class SpecificResponse extends BaseResponse {
    BaseModel baseResult;

    public SpecificResponse(int id , String timeStamp , String secondsTaken , String pagesExplored , String searchDepth , BaseModel model) {
        super(id , timeStamp , secondsTaken ,pagesExplored , searchDepth);
        this.baseResult = model;
    }
}
