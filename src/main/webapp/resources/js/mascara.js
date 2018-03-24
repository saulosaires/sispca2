//mascara generica em javascript  
function mascara(evento, mascara){  
      
    var campo, valor, i, tam, caracter;  
      
    if (document.all) // Internet Explorer  
        campo = evento.srcElement;  
    else // Nestcape, Mozzila  
         campo= evento.target;  
           
    valor = campo.value;  
    tam = valor.length;  
      
    for(i=0;i<mascara.length;i++){  
        caracter = mascara.charAt(i);  
        if(caracter!="9")   
            if(i<tam & caracter!=valor.charAt(i))  
                campo.value = valor.substring(0,i) + caracter + valor.substring(i,tam);  
                      
    }  
  
}

function ValidaNumero(e) {
	var tecla = (window.event) ? event.keyCode : e.which;
	if ((tecla > 47 && tecla < 58))
		return true;
	
	else {
		if (tecla == 8 || tecla == 0 || tecla == 9 || tecla == 11)
			return true;	
		else
			return false;
	}
}

function somenteNumeroDecimal(objTextBox, e) {
    var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode;
    if (e.which) {
        whichCode = e.which;
    } else {
        whichCode = e.keyCode;
    }
    if ((whichCode == 13) || (whichCode == 0) || (whichCode == 9) || (whichCode == 8)) return true;
    key = String.fromCharCode(whichCode); // Valor para o c�digo da Chave
    if (strCheck.indexOf(key) == -1) return false; // Chave inv�lida
    len = objTextBox.value.length;
    for (i = 0; i < len; i++)
        if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != ",")) break;
    aux = '';
    for (; i < len; i++)
        if (strCheck.indexOf(objTextBox.value.charAt(i)) != -1) aux += objTextBox.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0) objTextBox.value = '';
    if (len == 1) objTextBox.value = '0' + "," + '0' + aux;
    if (len == 2) objTextBox.value = '0' + "," + aux;
    if (len > 2 && len < 13) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += ".";
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        objTextBox.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
            objTextBox.value += aux2.charAt(i);
        objTextBox.value += "," + aux.substr(len - 2, len);
    }
    return false;
}