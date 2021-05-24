package kz.sports.decathlon.io.impl;

import kz.sports.decathlon.io.Writer;
import kz.sports.decathlon.models.event.Event;
import kz.sports.decathlon.models.tournament.participant.Participant;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.util.List;

public class XMLWriter implements Writer {
    /*
    * CAUTION
    *
    * JAXB was removed from JDK 11, so let's use raw DocumentBuilder to build XML.
    *
    * */

    private DocumentBuilder documentBuilder;

    private Transformer transformer;

    public XMLWriter() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            documentBuilder = dbf.newDocumentBuilder();
            transformer = transformerFactory.newTransformer();
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        }

        assert transformer != null;
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    }


    @Override
    public void write(ArrayList<Participant> results) {

        Document document = prepareDocument(results);

        DOMSource source = new DOMSource(document);
        StreamResult console = new StreamResult(System.out);
        try {
            transformer.transform(source, console);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public Document prepareDocument(ArrayList<Participant> results) {
        Document document = documentBuilder.newDocument();

        Element root = document.createElementNS("kz.sports.decathlon", "TournamentResult");
        document.appendChild(root);

        for (Participant participant : results) {
            root.appendChild(createParticipant(document, participant));
        }
        return document;
    }

    private static Node createParticipant(Document doc, Participant participant) {

        Element participantEl = doc.createElement("Participant");

        participantEl.setAttribute("name", participant.getName());
        participantEl.setAttribute("place", participant.getPlace());
        participantEl.setAttribute("totalPoints", String.valueOf(participant.getTotalPoints()));

        String[] rawResults = participant.getRawResults();
        List<Event> events = participant.getEvents();

        for (int i = 0; i < rawResults.length; i++)
            participantEl.appendChild(createResultElement(doc, events.get(i), rawResults[i]));

        return participantEl;
    }

    private static Node createResultElement(Document doc, Event event,
                                          String rawResult) {

        Element eventEl = doc.createElement("EventResult");
        eventEl.setAttribute("name", event.getName());
        eventEl.setAttribute("type", event.getType().toString().toLowerCase());
        eventEl.appendChild(doc.createTextNode(rawResult));

        return eventEl;
    }

}
