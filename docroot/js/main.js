YUI().use('aui-datepicker', function(Y) {
	new Y.DatePicker({
		trigger : '.datepicker',
		popover : {
			zIndex : Liferay.zIndex.TOOLTIP
		},
		mask : '%Y-%m-%d',
		calendar : {
			minimumDate: new Date(),
//		 	maximumDate : new Date("3014/11/16")
		},
		after: {
			selectionChange: function(event) {				
				var activeInput = this.get('activeInput');
				var form = activeInput.ancestor('form');
				
				if (form) {
					var formValidator = Liferay.Form.get(form.get('id')).formValidator;
					if (formValidator) {
						formValidator.validateField(activeInput.get('name'));
					}
				}
				activeInput.blur();
			}
		}
	});
});