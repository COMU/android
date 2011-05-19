#ifndef MODELS.PY
#define MODELS.PY
from django.db import models

# Create your models here.

class Sehir(models.Model):
    adi = models.CharField(max_length=50)

class Ulke(models.Model):
    adi = models.CharField(max_length=50)

class Konum(models.Model):
    enlem = models.FloatField()
    boylam = models.FloatField()
    ulke = models.ForeignKey(Ulke)
    sehir = models.ForeignKey(Sehir)

class EgitimDurumu(models.Model):
    adi = models.CharField(max_length=50)

class IlgiAlanlari(models.Model):
    adi = models.CharField(max_length=50)

class Meslek(models.Model):
    adi = models.CharField(max_length=50)

class KullaniciDetaylari(models.Model):
    meslek = models.ForeignKey(Meslek)
    egitim_durumu = models.ForeignKey(EgitimDurumu)
    ilgi_alanlari = models.ForeignKey(IlgiAlanlari)
class KullaniciResmi(models.Model):
    kaynak = models.ImageField(upload_to = 'images')
class Kullanici(models.Model):
    dogrulama_id = models.CharField(max_length=30)
    ad = models.CharField(max_length=100)
    soyad = models.CharField(max_length=100)
    dogum_tarihi = models.DateTimeField(null = True)
    email = models.EmailField()
    parola = models.CharField(max_length=100)
    konum = models.ForeignKey(Konum,null = True)
    kullanici_detaylari = models.ForeignKey(KullaniciDetaylari,null = True)
    durum = models.NullBooleanField(False)
    resim = models.OneToOneField(KullaniciResmi, null = True)
