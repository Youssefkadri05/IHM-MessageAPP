//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2015.02.22 at 04:58:02 PM CET
//

package main.java.com.ubo.tp.message.datamodel.jaxb.bean.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MessageXml complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="MessageXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmissionDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Sender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageXml", propOrder = { "id", "emissionDate", "messageer", "text" })
@XmlRootElement
public class MessageXml {

	@XmlElement(name = "ID", required = true)
	protected String id;
	@XmlElement(name = "EmissionDate")
	protected long emissionDate;
	@XmlElement(name = "Sender", required = true)
	protected String messageer;
	@XmlElement(name = "Text", required = true)
	protected String text;

	/**
	 * Gets the value of the id property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getID() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setID(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the emissionDate property.
	 *
	 */
	public long getEmissionDate() {
		return emissionDate;
	}

	/**
	 * Sets the value of the emissionDate property.
	 *
	 */
	public void setEmissionDate(long value) {
		this.emissionDate = value;
	}

	/**
	 * Gets the value of the messageer property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSender() {
		return messageer;
	}

	/**
	 * Sets the value of the messageer property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setSender(String value) {
		this.messageer = value;
	}

	/**
	 * Gets the value of the text property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the value of the text property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setText(String value) {
		this.text = value;
	}

}