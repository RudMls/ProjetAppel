/*
 * Copyright 2022 [name of copyright owner]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.projetappel.dao;

import com.example.projetappel.model.Absence;
import com.example.projetappel.model.CoursInstance;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CoursInstanceDao extends DAO<CoursInstance> {

    public CoursInstanceDao() {
        super.setEntity(CoursInstance.class);
    }

    public List<CoursInstance> getCoursInstances(Integer etudiantId) {
        String hql = "select ci from  CoursInstance ci, Appartenir a, Groupe g " +
                    " where a.etudiant.id = :etudiantId " +
                    " and a.groupe.id = g.id " +
                    " and g.id = ci.groupe.id " ;
        List<CoursInstance> coursInstances = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<CoursInstance> query = session.createQuery(hql);
            query.setParameter("etudiantId",etudiantId);
            if (!query.getResultList().isEmpty()) {
                coursInstances = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coursInstances;
    }

    public List<CoursInstance> getCiCours(Integer etudiantId, Integer coursId) {
        String hql = "select ci from  CoursInstance ci, Appartenir a, Groupe g " +
                " where a.etudiant.id = :etudiantId " +
                " and ci.cours.id = :coursId " +
                " and a.groupe.id = g.id " +
                " and g.id = ci.groupe.id " ;
        List<CoursInstance> coursInstances = new ArrayList<>();
        try (Session session = getSession()){
            getTransaction(session);
            Query<CoursInstance> query = session.createQuery(hql);
            query.setParameter("etudiantId",etudiantId);
            query.setParameter("coursId",coursId);
            if (!query.getResultList().isEmpty()) {
                coursInstances = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coursInstances;
    }

}
