/**
 * The MIT License
 * Copyright (c) 2016 Population Register Centre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fi.vm.kapa.rova.ptv.model;

import java.util.List;

public class Address {

    private String latitude;
    private String longitude;
    private String coordinateState;
    private List<Label> postOfficeBox;
    private String postalCode;
    private List<Label> postOffice;
    private List<Label> streetAddress;
    private String streetNumber;
    private Label municipality;
    private String country;
    private List<Label> additionalInformation;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCoordinateState() {
        return coordinateState;
    }

    public void setCoordinateState(String coordinateState) {
        this.coordinateState = coordinateState;
    }

    public List<Label> getPostOfficeBox() {
        return postOfficeBox;
    }

    public void setPostOfficeBox(List<Label> postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<Label> getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(List<Label> postOffice) {
        this.postOffice = postOffice;
    }

    public List<Label> getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(List<Label> streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Label getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Label municipality) {
        this.municipality = municipality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Label> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<Label> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

}
