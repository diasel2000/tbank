package com.testbank.tbank.views.createclient;

import com.testbank.tbank.model.entity.Client;
import com.testbank.tbank.model.service.ClientService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.testbank.tbank.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "client", layout = MainView.class)
@PageTitle("Create Client")
@CssImport("./styles/views/createclient/create-client-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class CreateClientView extends Div {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField occupation = new TextField("Occupation");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<Client> binder = new Binder(Client.class);

    public CreateClientView(ClientService clientService) {
        addClassName("create-client-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            Client client = new Client();
            client.setFirstName(firstName.getValue());
            client.setLastName(lastName.getValue());
            clientService.saveClient(client);
            Notification.show("Person details stored.");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new Client());
    }

    private Component createTitle() {
        return new H3("Personal information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstName, lastName, occupation);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

}
