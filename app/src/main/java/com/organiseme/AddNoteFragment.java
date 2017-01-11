package com.organiseme;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/*** A simple {@link Fragment} subclass.
        */


public class AddNoteFragment extends Fragment {

    public static final String topic = "nameKey";
    public static final String noteContent = "phoneKey";
    public String fileName = "notes.xml";
    public AddNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Dodawanie notatek");
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        final EditText topicNote = (EditText)view.findViewById(R.id.editText);
        final EditText textNote = (EditText)view.findViewById(R.id.editText2);

        Context context = getActivity();
        File file = new File(context.getFilesDir(), fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //final RelativeLayout mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_add_note, container, false);
        Button saveButton = (Button) view.findViewById(R.id.button3);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t;
                String nc;

                t = topicNote.getText().toString();
                nc = textNote.getText().toString();
                Log.d("Topic", t);
                Log.d("Content", nc);


                Collection<Note> notes = new ArrayList<Note>();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = null;
                try {
                    builder = factory.newDocumentBuilder();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                try {
                    Document document = builder.parse(new File( fileName ));
                    document.getDocumentElement().normalize();
                    Element root = document.getDocumentElement();
                    notes.add(new Note());
                    for(Note note : notes){
                        Element newNote = document.createElement("note");

                        Element notetopic = document.createElement("topic");
                        notetopic.appendChild(document.createTextNode(note.getTopic()));
                        newNote.appendChild(notetopic);

                        Element notecontent = document.createElement("content");
                        notecontent.appendChild(document.createTextNode(note.getNoteContent()));
                        newNote.appendChild(notecontent);

                        root.appendChild(newNote);
                    }

                    Element newNote = document.createElement("note");

                    Element notetopic = document.createElement("topic");
                    notetopic.appendChild(document.createTextNode(t));
                    newNote.appendChild(notetopic);

                    Element notecontent = document.createElement("content");
                    notecontent.appendChild(document.createTextNode(nc));
                    newNote.appendChild(notecontent);

                    root.appendChild(newNote);

                    DOMSource source = new DOMSource(document);

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    StreamResult result = new StreamResult(fileName);
                    transformer.transform(source, result);
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TransformerConfigurationException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                }


                Fragment newFragment = new NotesFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        return view;
    }
}
