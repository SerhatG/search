package nl.aerius.search.wui.component;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Emit;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.annotations.component.PropDefault;
import com.axellience.vuegwt.core.annotations.component.Ref;
import com.axellience.vuegwt.core.annotations.component.Watch;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import jsinterop.annotations.JsMethod;

import nl.aerius.search.wui.domain.SearchSuggestion;
import nl.aerius.search.wui.i18n.SearchM;
import nl.aerius.search.wui.i18n.SearchMessages;
import nl.aerius.search.wui.resources.SearchImageResources;
import nl.aerius.search.wui.resources.SearchResources;
import nl.aerius.wui.vue.directives.VectorDirective;
import nl.aerius.wui.vue.transition.HorizontalCollapse;

@Component(components = {
    MapSearchComponent.class,
    HorizontalCollapse.class
}, directives = {
    VectorDirective.class
})
public class PopoutSearchComponent implements IsVueComponent {
  @Prop boolean auto;

  @Prop EventBus eventBus;

  @Data SearchMessages i18n = SearchM.messages();
  @Data SearchImageResources img = SearchResources.images();

  @Data boolean searchShowing = false;

  @Ref MapSearchComponent input;

  @JsMethod
  public boolean isSearchShowing() {
    return searchShowing;
  }

  @JsMethod
  public void toggle() {
    searchShowing = !searchShowing;
  }

  @PropDefault("auto")
  boolean autoDefault() {
    return true;
  }

  @Watch("searchShowing")
  public void onSearchShowingChange(final boolean neww) {
    if (neww) {
      input.focus();
    }
  }

  @JsMethod
  @Emit
  public void selectSuggestion(final SearchSuggestion value) {}
}
