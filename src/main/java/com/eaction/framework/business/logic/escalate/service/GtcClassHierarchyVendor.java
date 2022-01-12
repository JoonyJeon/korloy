//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2021.07.19 �ð� 10:49:36 AM KST 
//


package com.eaction.framework.business.logic.escalate.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="gtc_class" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="parent_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="node_name" type="{}muti_language_string"/>
 *                   &lt;element name="preferred_name" type="{}muti_language_string" minOccurs="0"/>
 *                   &lt;element name="modified_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="mapping_rule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sort_level" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="document_list" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="document" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="usage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="file_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
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
 *       &lt;attribute name="gtc_vendor_hierarchy_version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="gtc_generic_hierarchy_version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="plib_version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="time_stamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "gtcClass"
})
@XmlRootElement(name = "gtc_class_hierarchy_vendor")
public class GtcClassHierarchyVendor {

    @XmlElement(name = "gtc_class", required = true)
    protected List<GtcClassHierarchyVendor.GtcClass> gtcClass;
    @XmlAttribute(name = "gtc_vendor_hierarchy_version", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String gtcVendorHierarchyVersion;
    @XmlAttribute(name = "gtc_generic_hierarchy_version", required = true)
    protected String gtcGenericHierarchyVersion;
    @XmlAttribute(name = "plib_version", required = true)
    protected String plibVersion;
    @XmlAttribute(name = "time_stamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStamp;

    /**
     * Gets the value of the gtcClass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gtcClass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGtcClass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GtcClassHierarchyVendor.GtcClass }
     * 
     * 
     */
    public List<GtcClassHierarchyVendor.GtcClass> getGtcClass() {
        if (gtcClass == null) {
            gtcClass = new ArrayList<GtcClassHierarchyVendor.GtcClass>();
        }
        return this.gtcClass;
    }

    /**
     * gtcVendorHierarchyVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGtcVendorHierarchyVersion() {
        return gtcVendorHierarchyVersion;
    }

    /**
     * gtcVendorHierarchyVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGtcVendorHierarchyVersion(String value) {
        this.gtcVendorHierarchyVersion = value;
    }

    /**
     * gtcGenericHierarchyVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGtcGenericHierarchyVersion() {
        return gtcGenericHierarchyVersion;
    }

    /**
     * gtcGenericHierarchyVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGtcGenericHierarchyVersion(String value) {
        this.gtcGenericHierarchyVersion = value;
    }

    /**
     * plibVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlibVersion() {
        return plibVersion;
    }

    /**
     * plibVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlibVersion(String value) {
        this.plibVersion = value;
    }

    /**
     * timeStamp �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * timeStamp �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStamp(XMLGregorianCalendar value) {
        this.timeStamp = value;
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
     *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="parent_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="node_name" type="{}muti_language_string"/>
     *         &lt;element name="preferred_name" type="{}muti_language_string" minOccurs="0"/>
     *         &lt;element name="modified_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="mapping_rule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sort_level" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="document_list" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="document" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="usage" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="file_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
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
        "id",
        "parentId",
        "nodeName",
        "preferredName",
        "modifiedDate",
        "mappingRule",
        "sortLevel",
        "documentList"
    })
    public static class GtcClass {

        @XmlElement(required = true)
        protected String id;
        @XmlElement(name = "parent_id", required = true, nillable=true, defaultValue="")
        protected String parentId;
        @XmlElement(name = "node_name", required = true)
        protected MutiLanguageString nodeName;
        @XmlElement(name = "preferred_name")
        protected MutiLanguageString preferredName;
        @XmlElement(name = "modified_date", required = true, nillable=true, defaultValue="")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar modifiedDate;
        @XmlElement(name = "mapping_rule", nillable=true)
        protected String mappingRule;
        @XmlElement(name = "sort_level")
        protected Integer sortLevel;
        @XmlElement(name = "document_list")
        protected GtcClassHierarchyVendor.GtcClass.DocumentList documentList;

        /**
         * id �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * id �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

        /**
         * parentId �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getParentId() {
            return parentId;
        }

        /**
         * parentId �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setParentId(String value) {
            this.parentId = value;
        }

        /**
         * nodeName �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link MutiLanguageString }
         *     
         */
        public MutiLanguageString getNodeName() {
            return nodeName;
        }

        /**
         * nodeName �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link MutiLanguageString }
         *     
         */
        public void setNodeName(MutiLanguageString value) {
            this.nodeName = value;
        }

        /**
         * preferredName �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link MutiLanguageString }
         *     
         */
        public MutiLanguageString getPreferredName() {
            return preferredName;
        }

        /**
         * preferredName �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link MutiLanguageString }
         *     
         */
        public void setPreferredName(MutiLanguageString value) {
            this.preferredName = value;
        }

        /**
         * modifiedDate �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getModifiedDate() {
            return modifiedDate;
        }

        /**
         * modifiedDate �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setModifiedDate(XMLGregorianCalendar value) {
            this.modifiedDate = value;
        }

        /**
         * mappingRule �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMappingRule() {
            return mappingRule;
        }

        /**
         * mappingRule �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMappingRule(String value) {
            this.mappingRule = value;
        }

        /**
         * sortLevel �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getSortLevel() {
            return sortLevel;
        }

        /**
         * sortLevel �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setSortLevel(Integer value) {
            this.sortLevel = value;
        }

        /**
         * documentList �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link GtcClassHierarchyVendor.GtcClass.DocumentList }
         *     
         */
        public GtcClassHierarchyVendor.GtcClass.DocumentList getDocumentList() {
            return documentList;
        }

        /**
         * documentList �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link GtcClassHierarchyVendor.GtcClass.DocumentList }
         *     
         */
        public void setDocumentList(GtcClassHierarchyVendor.GtcClass.DocumentList value) {
            this.documentList = value;
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
         *         &lt;element name="document" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="usage" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="file_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
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
            "document"
        })
        public static class DocumentList {

            @XmlElement(required = true)
            protected List<GtcClassHierarchyVendor.GtcClass.DocumentList.Document> document;

            /**
             * Gets the value of the document property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the document property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getDocument().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link GtcClassHierarchyVendor.GtcClass.DocumentList.Document }
             * 
             * 
             */
            public List<GtcClassHierarchyVendor.GtcClass.DocumentList.Document> getDocument() {
                if (document == null) {
                    document = new ArrayList<GtcClassHierarchyVendor.GtcClass.DocumentList.Document>();
                }
                return this.document;
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
             *         &lt;element name="usage" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="file_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
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
                "usage",
                "format",
                "fileName",
                "location"
            })
            public static class Document {

                @XmlElement(required = true)
                protected String usage;
                @XmlElement(required = true)
                protected String format;
                @XmlElement(name = "file_name", required = true)
                protected String fileName;
                @XmlElement(required = true)
                @XmlSchemaType(name = "anyURI")
                protected String location;

                /**
                 * usage �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getUsage() {
                    return usage;
                }

                /**
                 * usage �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setUsage(String value) {
                    this.usage = value;
                }

                /**
                 * format �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFormat() {
                    return format;
                }

                /**
                 * format �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFormat(String value) {
                    this.format = value;
                }

                /**
                 * fileName �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFileName() {
                    return fileName;
                }

                /**
                 * fileName �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFileName(String value) {
                    this.fileName = value;
                }

                /**
                 * location �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getLocation() {
                    return location;
                }

                /**
                 * location �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setLocation(String value) {
                    this.location = value;
                }

            }

        }

    }

}
