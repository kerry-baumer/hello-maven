package org.surreal.lobster.sharedcore.presenter.rankRateStrategies;


public class EnlistedRatingStrategy extends BaseRankRateEntryStrategy {

	@Override
	public boolean shouldShow(String... values) {
		return isNavy(values) && isEnlisted(values) && values.length > 3 && !values[3].trim().isEmpty();
	}
	
	public EnlistedRatingStrategy() {
		list.add(genItem("   "));
		list.add(genItem("AG ", "AEROGRAPHERS MATE (AG)"));                                      
		list.add(genItem("PR ", "AIRCREW SURVIVAL EQUIPMENTMAN (PR)"));                          
		list.add(genItem("AN ", "AIRMAN (AN)"));                                                 
		list.add(genItem("AA ", "AIRMAN APPRENTICE (AA)"));                                      
		list.add(genItem("AR ", "AIRMAN RECRUIT (AR)"));                                         
		list.add(genItem("AC ", "AIR TRAFFIC CONTROLLER (AC)"));                                 
		list.add(genItem("AB ", "AVIATION BOATSWAIN'S MATE MASTER CHIEF"));                      
		list.add(genItem("ABH", "AVIATION BOATSWAINS MATE - AIRCRAFT HANDLING (ABH)"));          
		list.add(genItem("ABF", "AVIATION BOATSWAINS MATE - FUELS (ABF)"));                      
		list.add(genItem("ABE", "AVIATION BOATSWAINS MATE - LAUNCH AND RECOVERY EQUIPMENT (AB"));
		list.add(genItem("AE ", "AVIATION ELECTRICIANS MATE (AE)"));                             
		list.add(genItem("AT ", "AVIATION ELECTRONICS TECHNICIAN (AT)"));                        
		list.add(genItem("AD ", "AVIATION MACHINISTS MATE (AD)"));                               
		list.add(genItem("AZ ", "AVIATION MAINTENANCE ADMINISTRATIONMAN (AZ)"));                 
		list.add(genItem("AF ", "AVIATION MAINTENANCEMAN MASTER CHIEF (AF)"));                   
		list.add(genItem("AO ", "AVIATION ORDNANCEMAN (AO)"));                                   
		list.add(genItem("AK ", "AVIATION STOREKEEPER (AK)"));                                   
		list.add(genItem("AM ", "AVIATION STRUCTURAL MECHANIC (AM)"));                           
		list.add(genItem("AME", "AVIATION STRUCTURAL MECHANIC - SAFETY EQUIP (AME)"));           
		list.add(genItem("AS ", "AVIATION SUPPORT EQUIPMENT TECHNICIAN (AS)"));                  
		list.add(genItem("AW ", "AVIATION WARFARE SYSTEMS OPERATOR (AW)"));                      
		list.add(genItem("AV ", "AVIONICS TECHNICIAN MASTER CHIEF (AV)"));                       
		list.add(genItem("BM ", "BOATSWAINS MATE (BM)"));                                        
		list.add(genItem("BT ", "BOILER TECHNICIAN (BT)"));                                      
		list.add(genItem("BU ", "BUILDER (BU)"));                                                
		list.add(genItem("CE ", "CONSTRUCTION ELECTRICIAN (CE)"));                               
		list.add(genItem("CU ", "CONSTRUCTIONMAN MASTER CHIEF (CU)"));
		list.add(genItem("CM ", "CONSTRUCTION MECHANIC (CM)"));                                  
		list.add(genItem("CT ", "CRYPTOLOGIC TECHNICIAN (CT)"));                                 
		list.add(genItem("CTA", "CRYPTOLOGIC TECHNICIAN - ADMINISTRATION (CTA)"));               
		list.add(genItem("CTR", "CRYPTOLOGIC TECHNICIAN - COLLECTION (CTR)"));                   
		list.add(genItem("CTI", "CRYPTOLOGIC TECHNICIAN - INTERPRETIVE (CTI)"));                 
		list.add(genItem("CTM", "CRYPTOLOGIC TECHNICIAN - MAINTENANCE (CTM)"));                  
		list.add(genItem("CTN", "CRYPTOLOGIC TECHNICIAN - NETWORKS (CTN)"));                     
		list.add(genItem("CTT", "CRYPTOLOGIC TECHNICIAN - TECHNICAL (CTT)"));                    
		list.add(genItem("CS ", "CULINARY SPECIALIST (CS)"));                                    
		list.add(genItem("DC ", "DAMAGE CONTROLMAN (DC)"));                                      
		list.add(genItem("DN ", "DENTALMAN (DN)"));                                              
		list.add(genItem("DA ", "DENTALMAN APPRENTICE (DA)"));                                   
		list.add(genItem("DR ", "DENTALMAN RECRUIT (DR)"));                                      
		list.add(genItem("EM ", "ELECTRICIANS MATE (EM)"));                                      
		list.add(genItem("ET ", "ELECTRONICS TECHNICIAN (ET)"));                                 
		list.add(genItem("EA ", "ENGINEERING AIDE (EA)"));                                       
		list.add(genItem("EN ", "ENGINEMAN (EN)"));                                              
		list.add(genItem("EQ ", "EQUIPMENTMAN MASTER CHIEF (EQ)"));                              
		list.add(genItem("EO ", "EQUIPMENT OPERATOR (EO)"));                                     
		list.add(genItem("EOD", "EXPLOSIVE ORDNANCE DISPOSAL (EOD)"));                           
		list.add(genItem("FC ", "FIRE CONTROLMAN (FC)"));                                        
		list.add(genItem("FT ", "FIRE CONTROL TECHNICAN (FT)"));                                 
		list.add(genItem("FN ", "FIREMAN (FN)"));                                                
		list.add(genItem("FA ", "FIREMAN APPRENTICE (FA)"));                                     
		list.add(genItem("FR ", "FIREMAN RECRUIT (FR)"));                                        
		list.add(genItem("GS ", "GAS TURBINE SYSTEMS TECHNICIAN (GS)"));                         
		list.add(genItem("GSE", "GAS TURBINE SYSTEMS TECHNICIAN - ELECTRICAL (GSE)"));           
		list.add(genItem("GSM", "GAS TURBINE SYSTEMS TECHNICIAN - MECHANICAL (GSM)"));           
		list.add(genItem("GM ", "GUNNERS MATE (GM)"));                                           
		list.add(genItem("GMG", "GUNNERS MATE - GUNS (GMG)"));                                   
		list.add(genItem("GMM", "GUNNERS MATE - MISSILES (GMM)"));
		list.add(genItem("HM ", "HOSPITAL CORPSMAN (HM)"));                                      
		list.add(genItem("HN ", "HOSPITALMAN (HN)"));                                            
		list.add(genItem("HA ", "HOSPITALMAN APPRENTICE (HA)"));                                 
		list.add(genItem("HR ", "HOSPITALMAN RECRUIT (HR)"));                                    
		list.add(genItem("HT ", "HULL MAINTENANCE TECHNICIAN (HT)"));                            
		list.add(genItem("IT ", "INFORMATION SYSTEMS TECHNICIAN (IT)"));                         
		list.add(genItem("IS ", "INTELLIGENCE SPECIALIST (IS)"));                                
		list.add(genItem("IC ", "INTERIOR COMMUNICATIONS ELECTRICIAN (IC)"));                    
		list.add(genItem("LN ", "LEGALMAN (LN)"));                                               
		list.add(genItem("LS ", "LOGISTICS SPECIALIST"));                                        
		list.add(genItem("MR ", "MACHINERY REPAIRMAN (MR)"));
		list.add(genItem("MM ", "MACHINISTS MATE (MM)"));                                        
		list.add(genItem("MC ", "MASS COMMUNICATIONS SPECIALIST (MC)"));                         
		list.add(genItem("MA ", "MASTER-AT-ARMS (MA)"));                                         
		list.add(genItem("MN ", "MINEMAN (MN)"));                                                
		list.add(genItem("MT ", "MISSILEMAN TECHNICIAN (MT)"));                                  
		list.add(genItem("MU ", "MUSICIAN (MU)"));                                               
		list.add(genItem("NAC", "NAVAL AIRCREWMAN"));                                            
		list.add(genItem("NC ", "NAVY COUNSELOR (NC)"));                                         
		list.add(genItem("ND ", "NAVY DIVER (ND)"));                                             
		list.add(genItem("OS ", "OPERATIONS SPECIALIST (OS)"));                                  
		list.add(genItem("PS ", "PERSONNEL SPECIALIST (PS)"));                                   
		list.add(genItem("PC ", "POSTAL CLERK (PC)"));                                           
		list.add(genItem("PI ", "PRECISION INSTRUMENTMAN MASTER CHIEF (PI)"));                   
		list.add(genItem("QM ", "QUARTERMASTER (QM)"));                                          
		list.add(genItem("RP ", "RELIGIOUS PROGRAMS SPECIALIST (RP)"));                          
		list.add(genItem("SN ", "SEAMAN (SN)"));                                                 
		list.add(genItem("SA ", "SEAMAN APPRENTICE (SA)"));                                      
		list.add(genItem("SR ", "SEAMAN RECRUIT (SR)"));                                         
		list.add(genItem("SH ", "SHIPS SERVICEMAN (SH)"));                                       
		list.add(genItem("STS", "SONAR TECHNICIAN - SUBMARINE (STS)"));                          
		list.add(genItem("STG", "SONAR TECHNICIAN - SURFACE (STG)"));                            
		list.add(genItem("SB ", "SPECIAL WARFARE BOAT OPERATOR (SB)"));                          
		list.add(genItem("SO ", "SPECIAL WARFARE OPERATOR (SO)"));                               
		list.add(genItem("SW ", "STEELWORKER (SW)"));                                            
		list.add(genItem("SK ", "STOREKEEPER (SK)"));                                            
		list.add(genItem("TM ", "TORPEDOMANS MATE (TM)"));                                       
		list.add(genItem("UC ", "UTILITIES/CONSTRUCTIONMAN MASTER CHIEF (UC)"));                 
		list.add(genItem("UT ", "UTILITIESMAN (UT)"));                                           
		list.add(genItem("YN ", "YEOMAN (YN)"));                                                 
	}

}
