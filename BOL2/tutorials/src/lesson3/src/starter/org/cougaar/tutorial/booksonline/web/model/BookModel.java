/*
 * <copyright>
 *  Copyright 2000-2003 Cougaar Software, Inc.
 *  All Rights Reserved
 * </copyright>
 */


/*
 * Created on Jun 20, 2003
 *
 *
 */
package org.cougaar.tutorial.booksonline.web.model;


/**
 * Book Value Object
 *
 * @author ttschampel
 */
public class BookModel {
    private String title;
    private String author;
    private String isbn;
    private long endPrice;
    private String subject;
    private boolean inStock;
    private float listPrice;
    private float ourPrice;
    private int onOrder;
    private int shelf;
    private String publisher;
    private String TOC;
    private String length;
    private String publisherNotes;
    private int rank;
    private String affil;
    private String interview;
    private String shippingMessage;
    private int photoId;
    private int mediaType;
    private long releaseDate;
    private String dimension;
    private String reviewText;
    private float avgRating;
    private int numReviews;
    private String publisherCity;
    private String publisherState;
    private String publisherAddress;

    /**
     * Get Avg Book Rating
     *
     * @return average rating of the book
     */
    public float getAvgRating() {
        return avgRating;
    }


    /**
     * Set Avg Book Rating
     *
     * @param avgRating Average rating of the book
     */
    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }


    /**
     * Get number of reviews
     *
     * @return number of book reviews
     */
    public int getNumReviews() {
        return numReviews;
    }


    /**
     * Set Number of reviews
     *
     * @param numReviews Number of reviews
     */
    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }


    /**
     * Get Publisher address
     *
     * @return publisher address
     */
    public String getPublisherAddress() {
        return publisherAddress;
    }


    /**
     * Set publisher address
     *
     * @param publisherAddress publisher address
     */
    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }


    /**
     * Get Publisher city
     *
     * @return publisher city
     */
    public String getPublisherCity() {
        return publisherCity;
    }


    /**
     * Set Publisher City
     *
     * @param publisherCity publisher City
     */
    public void setPublisherCity(String publisherCity) {
        this.publisherCity = publisherCity;
    }


    /**
     * get publisher state
     *
     * @return publisher state
     */
    public String getPublisherState() {
        return publisherState;
    }


    /**
     * set publisher state
     *
     * @param publisherState publisher state
     */
    public void setPublisherState(String publisherState) {
        this.publisherState = publisherState;
    }


    /**
     * get review text
     *
     * @return review text
     */
    public String getReviewText() {
        return reviewText;
    }


    /**
     * set review text
     *
     * @param reviewText review text
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }


    /**
     *set media type
     *
     * @param mediaType media type
     */
    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }


    /**
     * set photo id
     *
     * @param photoId photo id
     */
    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }


    /**
     * Get Dimension
     *
     * @return Dimension
     */
    public String getDimension() {
        return dimension;
    }


    /**
     * Set Dimension
     *
     * @param dimension dimension
     */
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }


    /**
     * Get Release Date
     * 
     * @return release Date
     */
    public long getReleaseDate() {
        return releaseDate;
    }


    /**
     * Set Release date
     *
     * @param releaseDate release date
     */
    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }


    /**
     * Get Media type
     *
     * @return media type
     */
    public int getMediaType() {
        return mediaType;
    }


    /**
     * Get Photo id
     *
     * @return photo id
     */
    public int getPhotoId() {
        return photoId;
    }


    /**
     * get shipping message
     *
     * @return shipping message
     */
    public String getShippingMessage() {
        return shippingMessage;
    }


    /**
     * set shipping message
     *
     * @param shippingMessage shipping message
     */
    public void setShippingMessage(String shippingMessage) {
        this.shippingMessage = shippingMessage;
    }


    /**
     * get affil
     *
     * @return affil 
     */
    public String getAffil() {
        return affil;
    }


    /**
     * set affil
     *
     * @param affil affil
     */
    public void setAffil(String affil) {
        this.affil = affil;
    }


    /**
     * get interview
     *
     * @return interview
     */
    public String getInterview() {
        return interview;
    }


    /**
     * set interview
     *
     * @param interview interview
     */ 
    public void setInterview(String interview) {
        this.interview = interview;
    }


    /**
     * get rank
     *
     * @return rank
     */
    public int getRank() {
        return rank;
    }


    /**
     * set rank
     *
     * @param rank rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }


    /**
     * get length
     *
     * @return length
     */
    public String getLength() {
        return length;
    }


    /**
     * set length
     *
     * @param length length
     */
    public void setLength(String length) {
        this.length = length;
    }


    /**
     * get publisher notes
     *
     * @return notes
     */ 
    public String getPublisherNotes() {
        return publisherNotes;
    }


    /**
     * set publisher notes
     *
     * @param publisherNotes publisher notes
     */
    public void setPublisherNotes(String publisherNotes) {
        this.publisherNotes = publisherNotes;
    }


    /**
     * get table of contents
     *
     * @return table of contents
     */
    public String getTOC() {
        return TOC;
    }


    /**
     * set table of contents
     *
     * @param toc table of contents
     */
    public void setTOC(String toc) {
        TOC = toc;
    }


    /**
     * get publisher
     *
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }


    /**
     * set publisher
     *
     * @param publisher publisher
     */ 
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    /**
     * get on order
     *
     * @return on order
     */
    public int getOnOrder() {
        return onOrder;
    }


    /**
     * set on order
     *
     * @param onOrder on order
     */
    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }


    /**
     * get shelf
     *
     * @return shelf
     */
    public int getShelf() {
        return shelf;
    }


    /**
     * set shelf
     *
     * @param shelf shelf
     */
    public void setShelf(int shelf) {
        this.shelf = shelf;
    }


    /**
     * is in stock
     *
     * @return is in stock
     */
    public boolean isInStock() {
        return inStock;
    }


    /**
     * set in stock
     *
     * @param inStock in stock
     */
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }


    /**
     * get list price
     *
     * @return list price
     */
    public float getListPrice() {
        return listPrice;
    }


    /**
     * set list price
     *
     * @param listPrice list price
     */
    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }


    /**
     * get our price
     *
     * @return our price
     */
    public float getOurPrice() {
        return ourPrice;
    }


    /**
     * set our price
     *
     * @param ourPrice our price
     */
    public void setOurPrice(float ourPrice) {
        this.ourPrice = ourPrice;
    }


    /**
     * get subject
     *
     * @return subject
     */
    public String getSubject() {
        return subject;
    }


    /**
     * set subject
     *
     * @param subject subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }


    /**
     * get author
     *
     * @return author
     */
    public String getAuthor() {
        return author;
    }


    /**
     * set author
     *
     * @param author author
     */
    public void setAuthor(String author) {
        this.author = author;
    }


    /**
     * get isbn
     *
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }


    /**
     * set isbn
     *
     * @param isbn isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    /**
     * Get end price
     *
     * @return end price
     */
    public long getEndPrice() {
        return endPrice;
    }


    /**
     * set End Price
     *
     * @param endPrice end price
     */
    public void setEndPrice(long endPrice) {
        this.endPrice = endPrice;
    }


    /**
     * Get Title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Set Title
     *
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
