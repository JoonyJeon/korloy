//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2021.07.19 �ð� 10:50:11 AM KST 
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
 *         &lt;element name="item" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="product_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="gtc_generic_class_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="gtc_vendor_class_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="p21_value_change_timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="p21_structure_change_timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="p21_file_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="p21_file_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="effectivity_active_start_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="effectivity_active_end_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="replacement_product_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="gtc_generic_version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="unit_system" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "item"
})
@XmlRootElement(name = "package_assortment")
public class PackageAssortment {

    protected List<PackageAssortment.Item> item;

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageAssortment.Item }
     * 
     * 
     */
    public List<PackageAssortment.Item> getItem() {
        if (item == null) {
            item = new ArrayList<PackageAssortment.Item>();
        }
        return this.item;
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
     *         &lt;element name="product_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="gtc_generic_class_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="gtc_vendor_class_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="p21_value_change_timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="p21_structure_change_timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="p21_file_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="p21_file_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="effectivity_active_start_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="effectivity_active_end_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="replacement_product_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="gtc_generic_version" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="unit_system" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "productId",
        "gtcGenericClassId",
        "gtcVendorClassId",
        "p21ValueChangeTimestamp",
        "p21StructureChangeTimestamp",
        "p21FileName",
        "p21FileUrl",
        "effectivityActiveStartDate",
        "effectivityActiveEndDate",
        "replacementProductId",
        "gtcGenericVersion",
        "unitSystem"
    })
    public static class Item {

        @XmlElement(name = "product_id", required = true)
        protected String productId;
        @XmlElement(name = "gtc_generic_class_id", required = true)
        protected String gtcGenericClassId;
        @XmlElement(name = "gtc_vendor_class_id", required = true)
        protected String gtcVendorClassId;
        @XmlElement(name = "p21_value_change_timestamp", required = true, nillable=true)
        @XmlNullPolicy(emptyNodeRepresentsNull = true, nullRepresentationForXml = XmlMarshalNullRepresentation.EMPTY_NODE)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar p21ValueChangeTimestamp;
        @XmlElement(name = "p21_structure_change_timestamp", required = true, nillable=true)
        @XmlNullPolicy(emptyNodeRepresentsNull = true, nullRepresentationForXml = XmlMarshalNullRepresentation.EMPTY_NODE)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar p21StructureChangeTimestamp;
        @XmlElement(name = "p21_file_name", required = true, nillable=true)
        @XmlNullPolicy(emptyNodeRepresentsNull = true, nullRepresentationForXml = XmlMarshalNullRepresentation.EMPTY_NODE)
        protected String p21FileName;
        @XmlElement(name = "p21_file_url", nillable=true)
        @XmlNullPolicy(emptyNodeRepresentsNull = true, nullRepresentationForXml = XmlMarshalNullRepresentation.EMPTY_NODE)
        protected String p21FileUrl;
        @XmlElement(name = "effectivity_active_start_date")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar effectivityActiveStartDate;
        @XmlElement(name = "effectivity_active_end_date")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar effectivityActiveEndDate;
        @XmlElement(name = "replacement_product_id")
        protected String replacementProductId;
        @XmlElement(name = "gtc_generic_version", required = true)
        protected String gtcGenericVersion;
        @XmlElement(name = "unit_system", required = true)
        protected String unitSystem;

        /**
         * productId �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductId() {
            return productId;
        }

        /**
         * productId �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductId(String value) {
            this.productId = value;
        }

        /**
         * gtcGenericClassId �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGtcGenericClassId() {
            return gtcGenericClassId;
        }

        /**
         * gtcGenericClassId �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGtcGenericClassId(String value) {
            this.gtcGenericClassId = value;
        }

        /**
         * gtcVendorClassId �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGtcVendorClassId() {
            return gtcVendorClassId;
        }

        /**
         * gtcVendorClassId �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGtcVendorClassId(String value) {
            this.gtcVendorClassId = value;
        }

        /**
         * p21ValueChangeTimestamp �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getP21ValueChangeTimestamp() {
            return p21ValueChangeTimestamp;
        }

        /**
         * p21ValueChangeTimestamp �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setP21ValueChangeTimestamp(XMLGregorianCalendar value) {
            this.p21ValueChangeTimestamp = value;
        }

        /**
         * p21StructureChangeTimestamp �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getP21StructureChangeTimestamp() {
            return p21StructureChangeTimestamp;
        }

        /**
         * p21StructureChangeTimestamp �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setP21StructureChangeTimestamp(XMLGregorianCalendar value) {
            this.p21StructureChangeTimestamp = value;
        }

        /**
         * p21FileName �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getP21FileName() {
            return p21FileName;
        }

        /**
         * p21FileName �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setP21FileName(String value) {
            this.p21FileName = value;
        }

        /**
         * p21FileUrl �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getP21FileUrl() {
            return p21FileUrl;
        }

        /**
         * p21FileUrl �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setP21FileUrl(String value) {
            this.p21FileUrl = value;
        }

        /**
         * effectivityActiveStartDate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEffectivityActiveStartDate() {
            return effectivityActiveStartDate;
        }

        /**
         * effectivityActiveStartDate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEffectivityActiveStartDate(XMLGregorianCalendar value) {
            this.effectivityActiveStartDate = value;
        }

        /**
         * effectivityActiveEndDate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEffectivityActiveEndDate() {
            return effectivityActiveEndDate;
        }

        /**
         * effectivityActiveEndDate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEffectivityActiveEndDate(XMLGregorianCalendar value) {
            this.effectivityActiveEndDate = value;
        }

        /**
         * replacementProductId �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReplacementProductId() {
            return replacementProductId;
        }

        /**
         * replacementProductId �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReplacementProductId(String value) {
            this.replacementProductId = value;
        }

        /**
         * gtcGenericVersion �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGtcGenericVersion() {
            return gtcGenericVersion;
        }

        /**
         * gtcGenericVersion �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGtcGenericVersion(String value) {
            this.gtcGenericVersion = value;
        }

        /**
         * unitSystem �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnitSystem() {
            return unitSystem;
        }

        /**
         * unitSystem �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnitSystem(String value) {
            this.unitSystem = value;
        }

    }

}
