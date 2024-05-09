Sub No_04_A_INV_PORT_P()
' Macro 13.01.2021. by mlabrkic
' CTRL + p

'    Application.Run ("C:\...\01_INV_PORT\Pokreni_inventory_01_port.bat")
'    x = Shell("C:\...\01_INV_PORT\Pokreni_inventory_01_port.bat", vbNormalFocus)

  Dim x1 As Variant
  Dim sPath As String
  Dim sFile As String
  Dim sUtilsFolder As String

  Dim sSLA As String

  Dim sVendor As String, sModel As String
  Dim sUR_ID As String, sPort As String
  Dim sSlot As String, sPIC As String, sMIC As String

  Dim sDescription As String
  Dim iSLA As Integer, iPos As Integer

  Dim sVendor1 As String, sVendor2 As String, sVendor3 As String

  ' lower case
  sVendor1 = Worksheets("POPISI").Range("K1").Value ' C
  sVendor2 = Worksheets("POPISI").Range("L1").Value ' J
  sVendor3 = Worksheets("POPISI").Range("M1").Value

  sUtilsFolder = Worksheets("POPISI").Range("J4").Value

  sPath = sUtilsFolder & "\01_INV_PORT\"
  sFile = "Pokreni_inventory_01_port.bat"

  sSLA = Worksheets("RADNA").Range("B30").Value
  If (sSLA = "") Then
      iSLA = 0
  Else  ' SLA
      iSLA = 7
  End If

  sVendor = Worksheets("RADNA").Cells(26 + iSLA, 5).Value ' Korisnik se povezuje na router / switch vendora
'    sModel = Worksheets("RADNA").Cells(23 + iSLA, 4).Value ' ...
  sUR_ID = Worksheets("RADNA").Cells(26 + iSLA, 2).Value
  sPort = Worksheets("RADNA").Cells(27 + iSLA, 2).Value
  sDescription = Worksheets("RADNA").Cells(29 + iSLA, 2).Value

  If (sVendor = sVendor2) Then
      iPos = InStr(3, sPort, "/", 1)    ' Pozicija prvog "/" nakon 3 karaktera
      sSlot = Mid(sPort, 4, iPos - 4)
      sPIC = Mid(sPort, iPos + 1, 1)
      If (sPIC = 0) Or (sPIC = 1) Then
          sMIC = 0
      ElseIf (sPIC = 2) Or (sPIC = 3) Then
          sMIC = 1
      End If
      sSlot = Trim(sSlot & "/" & sMIC & "/" & sPIC)
  ElseIf (sVendor = sVendor3) Then
      sSlot = "0" + Mid(sPort, 4, 1) + "-LPU"
  End If

  Worksheets("RADNA").Cells(27 + iSLA, 3).Value = sSlot
  ActiveWorkbook.Save
  x1 = Shell(sPath + sFile + " " + sUR_ID + " " + sSlot + " " + Chr(34) & sDescription & Chr(34), vbNormalFocus)


'    umjesto " staviti:    & Chr(34) &"
'    Worksheets("Totals").Cells(cellCount + 10, 5).Formula = "=COUNTIF('" & cellCount & "'!G:G," & """H"")"
'
'    vba: umjesto ; u formulu ide ,
'    =CONCATENATE(LEFT(C32;FIND("/";C32)-1);"/";C33;"/";MID(C32;FIND("/";C32)+1;1))
'    Worksheets("DIS").Cells(34, 3).Formula = "=CONCATENATE(LEFT(C32,FIND('" / "',C32)-1),'" / "',C33,'" / "',MID(C32,FIND('" / "',C32)+1,1))"
'    isTemp1_blank = InStr(20, sTemp1_trim_blank, ":", 1)    ' Pozicija ":" nakon 20 karaktera

End Sub

