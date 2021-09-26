package com.example.wasteless;

public class OfferItem {

    private String OfferName;
    private String Servings;
    private String Address;
    private String Category;
    private String PickUp_From;
    private String PickUp_By;
    private String Description;
    private String mImageUrl;
    private String ReqOfferEmail;
    private String ReqOfferName;
    private String ReqOfferPhone;

    public String getReqOfferEmail() {
        return ReqOfferEmail;
    }

    public void setReqOfferEmail(String reqOfferEmail) {
        ReqOfferEmail = reqOfferEmail;
    }

    public String getReqOfferName() {
        return ReqOfferName;
    }

    public void setReqOfferName(String reqOfferName) {
        ReqOfferName = reqOfferName;
    }

    public String getReqOfferPhone() {
        return ReqOfferPhone;
    }

    public void setReqOfferPhone(String reqOfferPhone) {
        ReqOfferPhone = reqOfferPhone;
    }




    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;


   
    public OfferItem(String address, String category, String offerName, String pickUp_By, String pickUp_From, String servings, String description, String reqOfferName, String reqOfferEmail, String reqOfferPhone){
        Address = address;
        Category = category;
        OfferName = offerName;
        PickUp_By = pickUp_By;
        PickUp_From = pickUp_From;
        Servings = servings;
        Description = description;
        ReqOfferName = reqOfferName;
        ReqOfferEmail = reqOfferEmail;
        ReqOfferPhone = reqOfferPhone;
    }

    public OfferItem() {
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;

    }

    public String getOfferName() {
        return OfferName;
    }

    public void setOfferName(String offerName) {

        this.OfferName = offerName;
    }

    public String getServings() {
        return Servings;
    }

    public void setServings(String servings) {
        this.Servings = servings;
    }

    

    

    public String getPickUp_From() {
        return PickUp_From;
    }

    public void setPickUp_From(String pickUp_From) {
        this.PickUp_From = pickUp_From;
    }

    public String getPickUp_By() {
        return PickUp_By;
    }

    public void setPickUp_By(String pickUp_By) {
        this.PickUp_By = pickUp_By;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

   

}
