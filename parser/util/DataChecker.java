package parser.util;

import model.*;

import java.util.HashSet;
import java.util.Set;

public abstract class DataChecker {

    //Check all the data that must be entered
    public static boolean checkMandatoryData(Emplacement e) {
        if (e.getUsager().getCodeUsager() == 0) {
            return true;
        }
        if (e.getActivitePro().length() == 0) {
            return true;
        }
        if (e.getUsager().getStatutHabitat().length() == 0) {
            return true;
        }
        if (e.getDateEmmenagement() == null) {
            return true;
        }
        if (e.getAdresseEmplacement().getNom().length() == 0) {
            return true;
        }
        if (e.getAdresseEmplacement().getCp().length() == 0) {
            return true;
        }
        if (e.getAdresseEmplacement().getVille().length() == 0) {
            return true;
        }
        if (e.getAdresseEmplacement().getResidencePrincipaleSecondaire().length() == 0) {
            return true;
        }
        if (e.getAdresseEmplacement().getFacturation().length() == 0) {
            return true;
        }
        if (e.getUsager().getReglement().getCategorieTiers().length() == 0) {
            return true;
        }
        if (e.getUsager().getReglement().getNatureJuridique().length() == 0) {
            return true;
        }
        return false;
    }

    //Check the data that must be entered under particular conditions
    public static boolean checkConditionalMandatory(Emplacement e) {
        Contact c1 = e.getUsager().getContact1();
        Contact c2 = e.getUsager().getContact2();
        Set<Conteneur> conteneurs = e.getConteneurs();
        Carte carte = e.getUsager().getCarte();
        AdresseGenerique af = e.getUsager().getAdresseFacturation();
        Proprietaire p = e.getProprietaire();

        if (e.getActivitePro().equals("PARTICULIER")) {
            if (c1.getCivilite().length() == 0) {
                return true;
            }
            if (c1.getNomOuRaisonSociale().length() == 0) {
                return true;
            }
            if (c1.getPrenom().length() == 0) {
                return true;
            }
        }

        if (c2.getNomOuRaisonSociale().length() != 0) {
            if (c2.getCivilite().length() ==0) {
                return true;
            }
            if (c2.getPrenom().length() == 0) {
                return true;
            }
        }

        if (conteneurs != null) {
            for (Conteneur c : conteneurs) {
                if (c.isFilled()) {
                    //TODO
                    /*if (c.getDateDistribution() == null) {
                        return true;
                    }*/
                    if (c.getFluxOuMatiere().length() == 0) {
                        return true;
                    }
                    if (c.getVolumeBac().length() == 0) {
                        return true;
                    }
                    if (c.getNumeroPuce().length() == 0) {
                        return true;
                    }
                    if (c.getNumeroCuve().length() == 0) {
                        return true;
                    }
                }
            }
        }



        if (carte.getType().length() != 0) {
            if (carte.getNumero().length() == 0) {
                return true;
            }
            if (carte.getDateAttribution() == null) {
                return true;
            }
            if (carte.getMotifDistribution().length() == 0) {
                return true;
            }
        }


        if (e.getAdresseEmplacement().getFacturation().equals("NON")) {
            if (e.getUsager().getNomOuRaisonSocialeFacturation().length() == 0) {
                return true;
            }
            if (af.getNumEtLabelVoie().length() == 0) {
                return true;
            }
            if (af.getCp().length() == 0) {
                return true;
            }
            if (af.getVille().length() == 0) {
                return true;
            }
        }


        /*PICTBASE does not have a column for Code Propriétaire, generate ?

        if (p != null) {
            if (p.getCodeProprietaire().length() == 0) {
                return true;
            }
            if (p.getStatut().length() == 0) {
                return true;
            }
            if (p.getContact().getNomOuRaisonSociale().length() == 0) {
                return true;
            }
            if ((e.getActivitePro().equals("PARTICULIER")) && p.getContact().getCivilite().length() == 0) {
                return true;
            }
            if ((e.getActivitePro().equals("PARTICULIER")) && p.getContact().getPrenom().length() == 0) {
                return true;
            }
            if (p.getAdresse().getNumEtLabelVoie().length() == 0) {
                return true;
            }
            if (p.getAdresse().getCp().length() == 0) {
                return true;
            }
            if (p.getAdresse().getVille().length() == 0) {
                return true;
            }
        }
        */

        return false;
    }

    public static boolean checkTelFormat(Emplacement e) {
        Set<String> nums = new HashSet<>();
        nums.add(e.getUsager().getContact1().getTelephone1());
        nums.add(e.getUsager().getContact1().getTelephone2());
        nums.add(e.getProprietaire().getContact().getTelephone1());
        nums.add(e.getProprietaire().getContact().getTelephone2());

        if (nums.size() == 0) {
            return false;
        }

        for (String n : nums) {
            if ((n.length() != 0) && (n.length() != 10)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkEmailFormat(Emplacement e) {
        String email = e.getUsager().getContact1().getEmail();

        if (email.length() == 0) {
            return false;
        }
        if (!(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
            return true;
        }
        return false;
    }

    public static boolean checkSiretFormat(Emplacement e) {
        String siret = e.getUsager().getSiret();

        return checkCharNb(siret, 14);
    }

    public static boolean checkCpFormat(Emplacement e) {
        String cp1 = e.getAdresseEmplacement().getCp();
        String cp2 = e.getProprietaire().getAdresse().getCp();

        if (!(checkCharNb(cp1,5))) {
            return checkCharNb(cp2,5);
        }
        return true;
    }

    private static boolean checkCharNb(String s, int n) {
        if (s.length() == 0) {
            return false;
        }
        if (s.length() != n) {
            return true;
        }
        return false;
    }

    public static boolean checkHabitantsNumber(Emplacement e) {
        try {
            String s = e.getUsager().getNombreHabitants();

            if (s.length() == 0) {
                return false;
            }
            if (Integer.parseInt(s.replace(",00","")) > 10) {
                return true;
            }
        } catch (Exception exception) {
            return true;
        }

        return false;
    }
}
