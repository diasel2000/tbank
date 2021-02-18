package com.testbank.tbank.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @SequenceGenerator(name = "seq-gen", sequenceName = "MY_SEQ_GEN", initialValue = 205, allocationSize = 12)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity)) {
            return false; // null or other class
        }
        AbstractEntity other = (AbstractEntity) obj;

        if (id != null) {
            return id.equals(other.id);
        }
        return super.equals(other);
    }
}