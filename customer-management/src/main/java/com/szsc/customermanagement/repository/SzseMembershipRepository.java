package com.szsc.customermanagement.repository;

import com.szsc.customermanagement.entity.SzseMembership;
import com.szsc.customermanagement.exception.SzseMembershipNotFoundException;

import java.util.List;

public interface SzseMembershipRepository {
    List<SzseMembership> findAll();

    SzseMembership findByCompanyId(int companyId) throws SzseMembershipNotFoundException;

    void save(SzseMembership szseMembership);

    void update(SzseMembership szseMembership) throws SzseMembershipNotFoundException;

    void delete(SzseMembership szseMembership) throws SzseMembershipNotFoundException;
}