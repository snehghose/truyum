import { Component } from '@angular/core';

@Component({
  selector: 'app-not-found',
  template: `<div id='not-found' class="mt-2">
  <h3>The page you are looking for is not found</h3>
  </div>`,
  styles: [`#not-found{color:red}`]
})
export class NotFoundComponent{
}
