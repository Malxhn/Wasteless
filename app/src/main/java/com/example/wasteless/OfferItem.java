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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
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

    public OfferItem() {
    }

    public OfferItem(String OfferName, String servings, String address, String category, String PickUp_From, String PickUp_By, String Description, String imageUrl) {
        this.mImageUrl=imageUrl;
        this.OfferName = OfferName;
        this.Servings = servings;
        this.Address = address;
        this.Category = category;
        this.PickUp_From = PickUp_From;
        this.PickUp_By = PickUp_By;
        this.Description = Description;
    }
}
