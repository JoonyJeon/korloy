//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2021.07.19 �ð� 10:50:11 AM KST 
//


package com.eaction.framework.business.logic.escalate.service;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectAssortmentFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectAssortmentFactory() {
    }

    /**
     * Create an instance of {@link PackageAssortment }
     * 
     */
    public PackageAssortment createPackageAssortment() {
        return new PackageAssortment();
    }

    /**
     * Create an instance of {@link PackageAssortment.Item }
     * 
     */
    public PackageAssortment.Item createPackageAssortmentItem() {
        return new PackageAssortment.Item();
    }

}
