package com.testbank.tbank.views.createpayment;

import com.testbank.tbank.model.entity.Client;
import com.testbank.tbank.model.entity.Register;
import com.testbank.tbank.model.service.ClientService;
import com.testbank.tbank.model.service.RegisterService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.testbank.tbank.views.main.MainView;

@Route(value = "payments", layout = MainView.class)
@PageTitle("Create Payment")
@CssImport("./styles/views/createpayment/create-payment-view.css")
public class CreatePaymentView extends Div {

    private TextField sourceAccId = new TextField("Source");
    private TextField destAccId = new TextField("Dest");
    private IntegerField amount = new IntegerField("Amount");
    private TextField occupation = new TextField("Date");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<Client> binder = new Binder(Client.class);

    public CreatePaymentView(RegisterService registerService) {
        addClassName("create-payment-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            Register register = new Register();
            register.setAmount(amount.getValue());
            register.setDestId(destAccId.getValue());
            register.setSorceId(sourceAccId.getValue());
            register.setTimestamp(occupation.getValue());
            registerService.createPayment(register);
            Notification.show("Payment compleat.");
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
        formLayout.add(sourceAccId, destAccId, amount, occupation);
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
