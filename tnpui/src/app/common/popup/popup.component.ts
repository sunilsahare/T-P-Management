import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
declare var $: any; // Declare jQuery

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
})
export class PopupComponent implements OnInit {
  @Input() data: any = {};
  @Output() buttonClickEvent = new EventEmitter<string>();
  
  ngOnInit(): void {
    
  }
  
  openPopup(data: any): void {
    this.data = data;
    console.log('data',this.data);
    $('#popup').modal('show');
  }

  onButtonClick(value:string): void {
    this.data = null;
    $('#popup').modal('hide');
    this.buttonClickEvent.emit(value);
  }

}
