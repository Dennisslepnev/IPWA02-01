package de.iu.likeherotozero.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.iu.likeherotozero.entity.Emission;
import de.iu.likeherotozero.enums.EmissionStatus;
import de.iu.likeherotozero.enums.UserRole;
import de.iu.likeherotozero.service.EmissionService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ApprovalBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmissionService emissionService;

    @Inject
    private LoginBean loginBean;

    private List<Emission> pendingEmissions = new ArrayList<>();

    private String successMessage;
    private String errorMessage;

    @PostConstruct
    public void init() {

        if (isEditor()) {
            loadPendingEmissions();
        }
    }

    public void approve(Long emissionId) {

        if (!isEditor()) {
            errorMessage = "Für die Freigabe ist die Rolle EDITOR erforderlich.";
            return;
        }

        try {
            emissionService.updateStatus(
                    emissionId,
                    EmissionStatus.APPROVED);

            successMessage = "Der Datensatz wurde freigegeben.";
            errorMessage = null;

            loadPendingEmissions();

        } catch (RuntimeException e) {
            successMessage = null;
            errorMessage = "Der Datensatz konnte nicht freigegeben werden.";
        }
    }

    public void reject(Long emissionId) {

        if (!isEditor()) {
            errorMessage = "Für die Ablehnung ist die Rolle EDITOR erforderlich.";
            return;
        }

        try {
            emissionService.updateStatus(
                    emissionId,
                    EmissionStatus.REJECTED);

            successMessage = "Der Datensatz wurde abgelehnt.";
            errorMessage = null;

            loadPendingEmissions();

        } catch (RuntimeException e) {
            successMessage = null;
            errorMessage = "Der Datensatz konnte nicht abgelehnt werden.";
        }
    }

    private void loadPendingEmissions() {
        pendingEmissions = emissionService.findPendingEmissions();
    }

    public boolean isEditor() {
        return loginBean.isLoggedIn()
                && loginBean.getLoggedInUser().getRole() == UserRole.EDITOR;
    }

    public List<Emission> getPendingEmissions() {
        return pendingEmissions;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}