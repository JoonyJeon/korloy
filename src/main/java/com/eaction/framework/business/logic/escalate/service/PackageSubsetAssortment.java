//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2021.07.19 �ð� 10:50:49 AM KST 
//


package com.eaction.framework.business.logic.escalate.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="subset" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="subset_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="subset_name" type="{}muti_language_string"/>
 *                   &lt;element name="description" type="{}muti_language_string" minOccurs="0"/>
 *                   &lt;element name="items">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="product_id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
    "subset"
})
@XmlRootElement(name = "package_subset_assortment")
public class PackageSubsetAssortment {

    protected List<PackageSubsetAssortment.Subset> subset;

    /**
     * Gets the value of the subset property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subset property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubset().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageSubsetAssortment.Subset }
     * 
     * 
     */
    public List<PackageSubsetAssortment.Subset> getSubset() {
        if (subset == null) {
            subset = new ArrayList<PackageSubsetAssortment.Subset>();
        }
        return this.subset;
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
     *         &lt;element name="subset_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="subset_name" type="{}muti_language_string"/>
     *         &lt;element name="description" type="{}muti_language_string" minOccurs="0"/>
     *         &lt;element name="items">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="product_id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
        "subsetId",
        "subsetName",
        "description",
        "items"
    })
    public static class Subset {

        @XmlElement(name = "subset_id", required = true)
        protected String subsetId;
        @XmlElement(name = "subset_name", required = true)
        protected MutiLanguageString subsetName;
        protected MutiLanguageString description;
        @XmlElement(required = true)
        protected PackageSubsetAssortment.Subset.Items items;

        /**
         * subsetId �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSubsetId() {
            return subsetId;
        }

        /**
         * subsetId �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSubsetId(String value) {
            this.subsetId = value;
        }

        /**
         * subsetName �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link MutiLanguageString }
         *     
         */
        public MutiLanguageString getSubsetName() {
            return subsetName;
        }

        /**
         * subsetName �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link MutiLanguageString }
         *     
         */
        public void setSubsetName(MutiLanguageString value) {
            this.subsetName = value;
        }

        /**
         * description �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link MutiLanguageString }
         *     
         */
        public MutiLanguageString getDescription() {
            return description;
        }

        /**
         * description �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link MutiLanguageString }
         *     
         */
        public void setDescription(MutiLanguageString value) {
            this.description = value;
        }

        /**
         * items �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link PackageSubsetAssortment.Subset.Items }
         *     
         */
        public PackageSubsetAssortment.Subset.Items getItems() {
            return items;
        }

        /**
         * items �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link PackageSubsetAssortment.Subset.Items }
         *     
         */
        public void setItems(PackageSubsetAssortment.Subset.Items value) {
            this.items = value;
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
         *         &lt;element name="product_id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
            "productId"
        })
        public static class Items {

            @XmlElement(name = "product_id", required = true)
            protected List<String> productId;

            /**
             * Gets the value of the productId property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the productId property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getProductId().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getProductId() {
                if (productId == null) {
                    productId = new ArrayList<String>();
                }
                return this.productId;
            }

        }

    }

}
