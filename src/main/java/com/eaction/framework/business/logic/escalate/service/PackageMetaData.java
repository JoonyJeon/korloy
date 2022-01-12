//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2021.07.19 �ð� 10:50:31 AM KST 
//


package com.eaction.framework.business.logic.escalate.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.persistence.oxm.annotations.XmlMarshalNullRepresentation;
import org.eclipse.persistence.oxm.annotations.XmlNullPolicy;


/**
 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="supported_gtc_generic_versions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vendor_hierarchy_version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vendor_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vendor_acronym" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gtc_package_creation_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="gtc_package_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="logo_url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="download_security" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="online_connection_configuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vendor_system_version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="language" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="language_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="short_description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="long_description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="disclaimer_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "supportedGtcGenericVersions",
    "vendorHierarchyVersion",
    "vendorName",
    "vendorAcronym",
    "gtcPackageCreationDate",
    "gtcPackageId",
    "logoUrl",
    "downloadSecurity",
    "onlineConnectionConfiguration",
    "vendorSystemVersion",
    "language"
})
@XmlRootElement(name = "package_meta_data")
public class PackageMetaData {

    @XmlElement(name = "supported_gtc_generic_versions", required = true)
    protected String supportedGtcGenericVersions;
    @XmlElement(name = "vendor_hierarchy_version", required = true)
    protected String vendorHierarchyVersion;
    @XmlElement(name = "vendor_name", required = true)
    protected String vendorName;
    @XmlElement(name = "vendor_acronym", required = true)
    protected String vendorAcronym;
    @XmlElement(name = "gtc_package_creation_date", required = true, nillable=true)
    @XmlNullPolicy(emptyNodeRepresentsNull = true, nullRepresentationForXml = XmlMarshalNullRepresentation.EMPTY_NODE)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar gtcPackageCreationDate;
    @XmlElement(name = "gtc_package_id", required = true)
    protected String gtcPackageId;
    @XmlElement(name = "logo_url", required = true)
    protected String logoUrl;
    @XmlElement(name = "download_security")
    protected String downloadSecurity;
    @XmlElement(name = "online_connection_configuration")
    protected String onlineConnectionConfiguration;
    @XmlElement(name = "vendor_system_version")
    protected String vendorSystemVersion;
    @XmlElement(required = true)
    protected List<PackageMetaData.Language> language;

    /**
     * supportedGtcGenericVersions �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportedGtcGenericVersions() {
        return supportedGtcGenericVersions;
    }

    /**
     * supportedGtcGenericVersions �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportedGtcGenericVersions(String value) {
        this.supportedGtcGenericVersions = value;
    }

    /**
     * vendorHierarchyVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorHierarchyVersion() {
        return vendorHierarchyVersion;
    }

    /**
     * vendorHierarchyVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorHierarchyVersion(String value) {
        this.vendorHierarchyVersion = value;
    }

    /**
     * vendorName �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * vendorName �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorName(String value) {
        this.vendorName = value;
    }

    /**
     * vendorAcronym �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorAcronym() {
        return vendorAcronym;
    }

    /**
     * vendorAcronym �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorAcronym(String value) {
        this.vendorAcronym = value;
    }

    /**
     * gtcPackageCreationDate �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGtcPackageCreationDate() {
        return gtcPackageCreationDate;
    }

    /**
     * gtcPackageCreationDate �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGtcPackageCreationDate(XMLGregorianCalendar value) {
        this.gtcPackageCreationDate = value;
    }

    /**
     * gtcPackageId �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGtcPackageId() {
        return gtcPackageId;
    }

    /**
     * gtcPackageId �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGtcPackageId(String value) {
        this.gtcPackageId = value;
    }

    /**
     * logoUrl �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * logoUrl �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoUrl(String value) {
        this.logoUrl = value;
    }

    /**
     * downloadSecurity �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDownloadSecurity() {
        return downloadSecurity;
    }

    /**
     * downloadSecurity �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDownloadSecurity(String value) {
        this.downloadSecurity = value;
    }

    /**
     * onlineConnectionConfiguration �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnlineConnectionConfiguration() {
        return onlineConnectionConfiguration;
    }

    /**
     * onlineConnectionConfiguration �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnlineConnectionConfiguration(String value) {
        this.onlineConnectionConfiguration = value;
    }

    /**
     * vendorSystemVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorSystemVersion() {
        return vendorSystemVersion;
    }

    /**
     * vendorSystemVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorSystemVersion(String value) {
        this.vendorSystemVersion = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the language property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLanguage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageMetaData.Language }
     * 
     * 
     */
    public List<PackageMetaData.Language> getLanguage() {
        if (language == null) {
            language = new ArrayList<PackageMetaData.Language>();
        }
        return this.language;
    }


    /**
     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
     * 
     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="language_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="short_description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="long_description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="disclaimer_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "languageCode",
        "shortDescription",
        "longDescription",
        "disclaimerUrl"
    })
    public static class Language {

        @XmlElement(name = "language_code", required = true)
        protected String languageCode;
        @XmlElement(name = "short_description", required = true)
        protected String shortDescription;
        @XmlElement(name = "long_description")
        protected String longDescription;
        @XmlElement(name = "disclaimer_url")
        protected String disclaimerUrl;

        /**
         * languageCode �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLanguageCode() {
            return languageCode;
        }

        /**
         * languageCode �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLanguageCode(String value) {
            this.languageCode = value;
        }

        /**
         * shortDescription �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShortDescription() {
            return shortDescription;
        }

        /**
         * shortDescription �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShortDescription(String value) {
            this.shortDescription = value;
        }

        /**
         * longDescription �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLongDescription() {
            return longDescription;
        }

        /**
         * longDescription �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLongDescription(String value) {
            this.longDescription = value;
        }

        /**
         * disclaimerUrl �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDisclaimerUrl() {
            return disclaimerUrl;
        }

        /**
         * disclaimerUrl �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDisclaimerUrl(String value) {
            this.disclaimerUrl = value;
        }

    }

}
