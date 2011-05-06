#!/usr/bin/python
# -*- coding: utf-8 -*-

from django.core.mail import EmailMessage
import base64
class Email:
    def gonder(self,subject, parola, durum_id, email):
        parola = "Parola : " + parola + "\n"
        
        link = "http://10.0.2.2:8000/api/aktiflestir/?param=%s" % base64.encodestring("email=" + email + "&" + "dogrulama_id=" + durum_id)
        body = parola + "Aktivasyon Linki : " + link
        email = EmailMessage(subject, body, to=[email])
        if email.send():
            return True
        return False


