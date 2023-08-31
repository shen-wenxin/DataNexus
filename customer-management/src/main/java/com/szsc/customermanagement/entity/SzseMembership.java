package com.szsc.customermanagement.entity;
import java.util.Objects;

public class SzseMembership {

    private int szseMemberId;
    private int companyId;
    private boolean isSzseMember;
    private String szseMemberCode;
    private String szseMemberAbbreviation;

    public int getSzseMemberId() {
        return szseMemberId;
    }

    public void setSzseMemberId(int szseMemberId) {
        this.szseMemberId = szseMemberId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public boolean isSzseMember() {
        return isSzseMember;
    }

    public void setSzseMember(boolean szseMember) {
        isSzseMember = szseMember;
    }

    public String getSzseMemberCode() {
        return szseMemberCode;
    }

    public void setSzseMemberCode(String szseMemberCode) {
        this.szseMemberCode = szseMemberCode;
    }

    public String getSzseMemberAbbreviation() {
        return szseMemberAbbreviation;
    }

    public void setSzseMemberAbbreviation(String szseMemberAbbreviation) {
        this.szseMemberAbbreviation = szseMemberAbbreviation;
    }

    // toString()
    @Override
    public String toString() {
        return "SzseMembership{" +
                "szseMemberId=" + szseMemberId +
                ", companyId=" + companyId +
                ", isSzseMember=" + isSzseMember +
                ", szseMemberCode='" + szseMemberCode + '\'' +
                ", szseMemberAbbreviation='" + szseMemberAbbreviation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SzseMembership that = (SzseMembership) o;
        return szseMemberId == that.szseMemberId &&
                companyId == that.companyId &&
                isSzseMember == that.isSzseMember &&
                Objects.equals(szseMemberCode, that.szseMemberCode) &&
                Objects.equals(szseMemberAbbreviation, that.szseMemberAbbreviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(szseMemberId, companyId, isSzseMember, szseMemberCode, szseMemberAbbreviation);
    }
}