package project.spring.myAct.vo;

public class MyActivityDTO {

	private int actIdx;
	private String id;
	private int myCommentCount;
	private int myArticleCount;
	private int givenHeart;
	private int myAttendent;
	private int myPurchase;
	private int drinkInsertCount;
	
	public int getActIdx() {
		return actIdx;
	}
	public void setActIdx(int actIdx) {
		this.actIdx = actIdx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMyCommentCount() {
		return myCommentCount;
	}
	public void setMyCommentCount(int myCommentCount) {
		this.myCommentCount = myCommentCount;
	}
	public int getMyArticleCount() {
		return myArticleCount;
	}
	public void setMyArticleCount(int myArticleCount) {
		this.myArticleCount = myArticleCount;
	}
	public int getGivenHeart() {
		return givenHeart;
	}
	public void setGivenHeart(int givenHeart) {
		this.givenHeart = givenHeart;
	}
	public int getMyAttendent() {
		return myAttendent;
	}
	public void setMyAttendent(int myAttendent) {
		this.myAttendent = myAttendent;
	}
	public int getMyPurchase() {
		return myPurchase;
	}
	public void setMyPurchase(int myPurchase) {
		this.myPurchase = myPurchase;
	}
	public int getDrinkInsertCount() {
		return drinkInsertCount;
	}
	public void setDrinkInsertCount(int drinkInsertCount) {
		this.drinkInsertCount = drinkInsertCount;
	}
}
