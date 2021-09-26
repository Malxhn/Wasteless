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

    public OfferItem(String offerName, String servings, String address, String category, String pickUp_From, String pickUp_By, String description, String mImageUrl, String reqOfferEmail, String reqOfferName, String reqOfferPhone) {
        OfferName = offerName;
        Servings = servings;
        Address = address;
        Category = category;
        PickUp_From = pickUp_From;
        PickUp_By = pickUp_By;
        Description = description;
        this.mImageUrl = mImageUrl;
        ReqOfferEmail = reqOfferEmail;
        ReqOfferName = reqOfferName;
        ReqOfferPhone = reqOfferPhone;
    }

    public OfferItem() {
    }


    public String getOfferName() {
        return OfferName;
    }

    public void setOfferName(String offerName) {
        OfferName = offerName;
    }

    public String getServings() {
        return Servings;
    }

    public void setServings(String servings) {
        Servings = servings;
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

    public String getPickUp_From() {
        return PickUp_From;
    }

    public void setPickUp_From(String pickUp_From) {
        PickUp_From = pickUp_From;
    }

    public String getPickUp_By() {
        return PickUp_By;
    }

    public void setPickUp_By(String pickUp_By) {
        PickUp_By = pickUp_By;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

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
}
