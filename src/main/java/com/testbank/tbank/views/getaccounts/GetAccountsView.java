package com.testbank.tbank.views.getaccounts;

import java.util.Optional;

import com.testbank.tbank.model.entity.Account;
import com.testbank.tbank.model.service.AccountService;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

@Route(value = "account", layout = MainView.class)
@PageTitle("Get Accounts")
@CssImport("./styles/views/getaccounts/get-accounts-view.css")
public class GetAccountsView extends Div {

    private Grid<Account> grid = new Grid<>(Account.class, false);

    private TextField accountId;
    private TextField accountNum;
    private TextField type;
    private TextField balance;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Get");

    private BeanValidationBinder<Account> binder;

    private Account account;

    public GetAccountsView(@Autowired AccountService accountService) {
        addClassName("get-accounts-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("accountId").setAutoWidth(true);
        grid.addColumn("accountNum").setAutoWidth(true);
        grid.addColumn("type").setAutoWidth(true);
        grid.addColumn("balance").setAutoWidth(true);
        //grid.setDataProvider(new CrudServiceDataProvider<>(accountService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Account> accountFromBackend = accountService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (accountFromBackend.isPresent()) {
                    populateForm(accountFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Account.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.forField(accountId).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("accountId");
        binder.forField(accountNum).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("accountNum");
        binder.forField(balance).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("balance");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.account == null) {
                    this.account = new Account();
                }
                binder.writeBean(this.account);

                accountService.update(this.account);
                clearForm();
                refreshGrid();
                Notification.show("Account details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the account details.");
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
        accountId = new TextField("Account Id");
        accountNum = new TextField("Account Num");
        type = new TextField("Type");
        balance = new TextField("Balance");
        Component[] fields = new Component[]{accountId, accountNum, type, balance};

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

    private void populateForm(Account value) {
        this.account = value;
        binder.readBean(this.account);

    }
}
