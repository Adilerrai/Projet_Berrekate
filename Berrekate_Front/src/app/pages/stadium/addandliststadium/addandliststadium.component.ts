import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ApidbService } from 'src/app/services/apidb.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-addandliststadium',
  templateUrl: './addandliststadium.component.html',
  styleUrls: ['./addandliststadium.component.css']
})
export class AddandliststadiumComponent implements OnInit {
  StadiumForm: FormGroup;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  
  tableVente: [] = [];
  ventes:any[]=[];
  currentventes: any = JSON.parse(localStorage.getItem('currentventes') || '{}');
  dataSource = new MatTableDataSource(this.tableVente);

  displayedColumns: string[] = ['id','prenom', 'nom','telephone','tarif','date_creation','status','payer','actions'];
  constructor(
    private apidb: ApidbService,
  ) { 
    this.StadiumForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      city: new FormControl('', [Validators.required]),
      country: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
  }
  ajouterstadium() {
    
    // if (this.VenteForm.valid) {
    //   debugger
     var name = this.StadiumForm.get('name')?.value;
     var city= this.StadiumForm.get('city')?.value;
     var country= this.StadiumForm.get('country')?.value;
     var address= this.StadiumForm.get('address')?.value;


      //-------------
      this.apidb.addEnregistrement('vente/', {
        "name":name ,
        "city":city,
        "country":country,
        "address":address,

      }).subscribe(
        (data) => {
          Swal.fire('', 'Stadium ajouté avec succés', 'success');
          this.StadiumForm.reset();
        },
        err => {
          Swal.fire('', 'Erreur d\'ajout', 'error');
        }
      );
    }
}
