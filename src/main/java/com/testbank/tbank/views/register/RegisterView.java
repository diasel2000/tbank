package com.testbank.tbank.views.register;

import java.util.Optional;

import com.testbank.tbank.model.entity.Register;
import com.testbank.tbank.model.service.RegisterService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;
import com.testbank.tbank.views.main.MainView;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

@Route(value = "register", layout = MainView.class)
@PageTitle("Register")
@CssImport("./styles/views/register/register-view.css")
public class RegisterView extends Div {

    private Grid<Register> grid = new Grid<>(Register.class, false);

    private TextField paymentId;
    private DatePicker timestamp;
    private TextField sorceId;
    private TextField destId;
    private TextField amount;
    private TextField clientPayer;
    private TextField clientRecipient;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Register> binder;

    private Register register;

    public RegisterView(@Autowired RegisterService registerService) {
        addClassName("register-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("paymentId").setAutoWidth(true);
        grid.addColumn("timestamp").setAutoWidth(true);
        grid.addColumn("sorceId").setAutoWidth(true);
        grid.addColumn("destId").setAutoWidth(true);
        grid.addColumn("amount").setAutoWidth(true);
        grid.addColumn("clientPayer").setAutoWidth(true);
        grid.addColumn("clientRecipient").setAutoWidth(true);
        grid.setDataProvider(new CrudServiceDataProvider<>(registerService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Register> registerFromBackend = registerService.get(Integer.valueOf(event.getValue().getId()));
                // when a row is selected but the data is no longer available, refresh grid
                if (registerFromBackend.isPresent()) {
                    populateForm(registerFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Register.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.forField(paymentId).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("paymentId");
        binder.forField(sorceId).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("sorceId");
        binder.forField(destId).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("destId");
        binder.forField(amount).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("amount");
        binder.forField(clientPayer).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("clientPayer");
        binder.forField(clientRecipient).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("clientRecipient");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.register == null) {
                    this.register = new Register();
                }
                binder.writeBean(this.register);

                registerService.update(this.register);
                clearForm();
                refreshGrid();
                Notification.show("Register details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the register details.");
            }
        });

    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        paymentId = new TextField("Payment Id");
        timestamp = new DatePicker("Timestamp");
        sorceId = new TextField("Sorce Id");
        destId = new TextField("Dest Id");
        amount = new TextField("Amount");
        clientPayer = new TextField("Client Payer");
        clientRecipient = new TextField("Client Recipient");
        Component[] fields = new Component[]{paymentId, timestamp, sorceId, destId, amount, clientPayer,
                clientRecipient};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Register value) {
        this.register = value;
        binder.readBean(this.register);

    }
}
