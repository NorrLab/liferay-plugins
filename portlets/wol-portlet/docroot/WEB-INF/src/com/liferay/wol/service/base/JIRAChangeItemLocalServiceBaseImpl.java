/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.wol.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;

import com.liferay.wol.model.JIRAChangeItem;
import com.liferay.wol.service.JIRAActionLocalService;
import com.liferay.wol.service.JIRAActionLocalServiceFactory;
import com.liferay.wol.service.JIRAChangeGroupLocalService;
import com.liferay.wol.service.JIRAChangeGroupLocalServiceFactory;
import com.liferay.wol.service.JIRAChangeItemLocalService;
import com.liferay.wol.service.JIRAIssueLocalService;
import com.liferay.wol.service.JIRAIssueLocalServiceFactory;
import com.liferay.wol.service.SVNRepositoryLocalService;
import com.liferay.wol.service.SVNRepositoryLocalServiceFactory;
import com.liferay.wol.service.SVNRevisionLocalService;
import com.liferay.wol.service.SVNRevisionLocalServiceFactory;
import com.liferay.wol.service.WallEntryLocalService;
import com.liferay.wol.service.WallEntryLocalServiceFactory;
import com.liferay.wol.service.persistence.JIRAActionFinder;
import com.liferay.wol.service.persistence.JIRAActionFinderUtil;
import com.liferay.wol.service.persistence.JIRAActionPersistence;
import com.liferay.wol.service.persistence.JIRAActionUtil;
import com.liferay.wol.service.persistence.JIRAChangeGroupFinder;
import com.liferay.wol.service.persistence.JIRAChangeGroupFinderUtil;
import com.liferay.wol.service.persistence.JIRAChangeGroupPersistence;
import com.liferay.wol.service.persistence.JIRAChangeGroupUtil;
import com.liferay.wol.service.persistence.JIRAChangeItemPersistence;
import com.liferay.wol.service.persistence.JIRAChangeItemUtil;
import com.liferay.wol.service.persistence.JIRAIssueFinder;
import com.liferay.wol.service.persistence.JIRAIssueFinderUtil;
import com.liferay.wol.service.persistence.JIRAIssuePersistence;
import com.liferay.wol.service.persistence.JIRAIssueUtil;
import com.liferay.wol.service.persistence.SVNRepositoryPersistence;
import com.liferay.wol.service.persistence.SVNRepositoryUtil;
import com.liferay.wol.service.persistence.SVNRevisionPersistence;
import com.liferay.wol.service.persistence.SVNRevisionUtil;
import com.liferay.wol.service.persistence.WallEntryFinder;
import com.liferay.wol.service.persistence.WallEntryFinderUtil;
import com.liferay.wol.service.persistence.WallEntryPersistence;
import com.liferay.wol.service.persistence.WallEntryUtil;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * <a href="JIRAChangeItemLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class JIRAChangeItemLocalServiceBaseImpl
	implements JIRAChangeItemLocalService, InitializingBean {
	public JIRAChangeItem addJIRAChangeItem(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		jiraChangeItem.setNew(true);

		return jiraChangeItemPersistence.update(jiraChangeItem, false);
	}

	public void deleteJIRAChangeItem(long jiraChangeItemId)
		throws PortalException, SystemException {
		jiraChangeItemPersistence.remove(jiraChangeItemId);
	}

	public void deleteJIRAChangeItem(JIRAChangeItem jiraChangeItem)
		throws PortalException, SystemException {
		jiraChangeItemPersistence.remove(jiraChangeItem);
	}

	public List<JIRAChangeItem> dynamicQuery(
		DynamicQueryInitializer queryInitializer) throws SystemException {
		return jiraChangeItemPersistence.findWithDynamicQuery(queryInitializer);
	}

	public List<JIRAChangeItem> dynamicQuery(
		DynamicQueryInitializer queryInitializer, int start, int end)
		throws SystemException {
		return jiraChangeItemPersistence.findWithDynamicQuery(queryInitializer,
			start, end);
	}

	public JIRAChangeItem updateJIRAChangeItem(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		jiraChangeItem.setNew(false);

		return jiraChangeItemPersistence.update(jiraChangeItem, true);
	}

	public JIRAActionLocalService getJIRAActionLocalService() {
		return jiraActionLocalService;
	}

	public void setJIRAActionLocalService(
		JIRAActionLocalService jiraActionLocalService) {
		this.jiraActionLocalService = jiraActionLocalService;
	}

	public JIRAActionPersistence getJIRAActionPersistence() {
		return jiraActionPersistence;
	}

	public void setJIRAActionPersistence(
		JIRAActionPersistence jiraActionPersistence) {
		this.jiraActionPersistence = jiraActionPersistence;
	}

	public JIRAActionFinder getJIRAActionFinder() {
		return jiraActionFinder;
	}

	public void setJIRAActionFinder(JIRAActionFinder jiraActionFinder) {
		this.jiraActionFinder = jiraActionFinder;
	}

	public JIRAChangeGroupLocalService getJIRAChangeGroupLocalService() {
		return jiraChangeGroupLocalService;
	}

	public void setJIRAChangeGroupLocalService(
		JIRAChangeGroupLocalService jiraChangeGroupLocalService) {
		this.jiraChangeGroupLocalService = jiraChangeGroupLocalService;
	}

	public JIRAChangeGroupPersistence getJIRAChangeGroupPersistence() {
		return jiraChangeGroupPersistence;
	}

	public void setJIRAChangeGroupPersistence(
		JIRAChangeGroupPersistence jiraChangeGroupPersistence) {
		this.jiraChangeGroupPersistence = jiraChangeGroupPersistence;
	}

	public JIRAChangeGroupFinder getJIRAChangeGroupFinder() {
		return jiraChangeGroupFinder;
	}

	public void setJIRAChangeGroupFinder(
		JIRAChangeGroupFinder jiraChangeGroupFinder) {
		this.jiraChangeGroupFinder = jiraChangeGroupFinder;
	}

	public JIRAChangeItemPersistence getJIRAChangeItemPersistence() {
		return jiraChangeItemPersistence;
	}

	public void setJIRAChangeItemPersistence(
		JIRAChangeItemPersistence jiraChangeItemPersistence) {
		this.jiraChangeItemPersistence = jiraChangeItemPersistence;
	}

	public JIRAIssueLocalService getJIRAIssueLocalService() {
		return jiraIssueLocalService;
	}

	public void setJIRAIssueLocalService(
		JIRAIssueLocalService jiraIssueLocalService) {
		this.jiraIssueLocalService = jiraIssueLocalService;
	}

	public JIRAIssuePersistence getJIRAIssuePersistence() {
		return jiraIssuePersistence;
	}

	public void setJIRAIssuePersistence(
		JIRAIssuePersistence jiraIssuePersistence) {
		this.jiraIssuePersistence = jiraIssuePersistence;
	}

	public JIRAIssueFinder getJIRAIssueFinder() {
		return jiraIssueFinder;
	}

	public void setJIRAIssueFinder(JIRAIssueFinder jiraIssueFinder) {
		this.jiraIssueFinder = jiraIssueFinder;
	}

	public SVNRepositoryLocalService getSVNRepositoryLocalService() {
		return svnRepositoryLocalService;
	}

	public void setSVNRepositoryLocalService(
		SVNRepositoryLocalService svnRepositoryLocalService) {
		this.svnRepositoryLocalService = svnRepositoryLocalService;
	}

	public SVNRepositoryPersistence getSVNRepositoryPersistence() {
		return svnRepositoryPersistence;
	}

	public void setSVNRepositoryPersistence(
		SVNRepositoryPersistence svnRepositoryPersistence) {
		this.svnRepositoryPersistence = svnRepositoryPersistence;
	}

	public SVNRevisionLocalService getSVNRevisionLocalService() {
		return svnRevisionLocalService;
	}

	public void setSVNRevisionLocalService(
		SVNRevisionLocalService svnRevisionLocalService) {
		this.svnRevisionLocalService = svnRevisionLocalService;
	}

	public SVNRevisionPersistence getSVNRevisionPersistence() {
		return svnRevisionPersistence;
	}

	public void setSVNRevisionPersistence(
		SVNRevisionPersistence svnRevisionPersistence) {
		this.svnRevisionPersistence = svnRevisionPersistence;
	}

	public WallEntryLocalService getWallEntryLocalService() {
		return wallEntryLocalService;
	}

	public void setWallEntryLocalService(
		WallEntryLocalService wallEntryLocalService) {
		this.wallEntryLocalService = wallEntryLocalService;
	}

	public WallEntryPersistence getWallEntryPersistence() {
		return wallEntryPersistence;
	}

	public void setWallEntryPersistence(
		WallEntryPersistence wallEntryPersistence) {
		this.wallEntryPersistence = wallEntryPersistence;
	}

	public WallEntryFinder getWallEntryFinder() {
		return wallEntryFinder;
	}

	public void setWallEntryFinder(WallEntryFinder wallEntryFinder) {
		this.wallEntryFinder = wallEntryFinder;
	}

	public void afterPropertiesSet() {
		if (jiraActionLocalService == null) {
			jiraActionLocalService = JIRAActionLocalServiceFactory.getImpl();
		}

		if (jiraActionPersistence == null) {
			jiraActionPersistence = JIRAActionUtil.getPersistence();
		}

		if (jiraActionFinder == null) {
			jiraActionFinder = JIRAActionFinderUtil.getFinder();
		}

		if (jiraChangeGroupLocalService == null) {
			jiraChangeGroupLocalService = JIRAChangeGroupLocalServiceFactory.getImpl();
		}

		if (jiraChangeGroupPersistence == null) {
			jiraChangeGroupPersistence = JIRAChangeGroupUtil.getPersistence();
		}

		if (jiraChangeGroupFinder == null) {
			jiraChangeGroupFinder = JIRAChangeGroupFinderUtil.getFinder();
		}

		if (jiraChangeItemPersistence == null) {
			jiraChangeItemPersistence = JIRAChangeItemUtil.getPersistence();
		}

		if (jiraIssueLocalService == null) {
			jiraIssueLocalService = JIRAIssueLocalServiceFactory.getImpl();
		}

		if (jiraIssuePersistence == null) {
			jiraIssuePersistence = JIRAIssueUtil.getPersistence();
		}

		if (jiraIssueFinder == null) {
			jiraIssueFinder = JIRAIssueFinderUtil.getFinder();
		}

		if (svnRepositoryLocalService == null) {
			svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getImpl();
		}

		if (svnRepositoryPersistence == null) {
			svnRepositoryPersistence = SVNRepositoryUtil.getPersistence();
		}

		if (svnRevisionLocalService == null) {
			svnRevisionLocalService = SVNRevisionLocalServiceFactory.getImpl();
		}

		if (svnRevisionPersistence == null) {
			svnRevisionPersistence = SVNRevisionUtil.getPersistence();
		}

		if (wallEntryLocalService == null) {
			wallEntryLocalService = WallEntryLocalServiceFactory.getImpl();
		}

		if (wallEntryPersistence == null) {
			wallEntryPersistence = WallEntryUtil.getPersistence();
		}

		if (wallEntryFinder == null) {
			wallEntryFinder = WallEntryFinderUtil.getFinder();
		}
	}

	protected JIRAActionLocalService jiraActionLocalService;
	protected JIRAActionPersistence jiraActionPersistence;
	protected JIRAActionFinder jiraActionFinder;
	protected JIRAChangeGroupLocalService jiraChangeGroupLocalService;
	protected JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	protected JIRAChangeGroupFinder jiraChangeGroupFinder;
	protected JIRAChangeItemPersistence jiraChangeItemPersistence;
	protected JIRAIssueLocalService jiraIssueLocalService;
	protected JIRAIssuePersistence jiraIssuePersistence;
	protected JIRAIssueFinder jiraIssueFinder;
	protected SVNRepositoryLocalService svnRepositoryLocalService;
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	protected SVNRevisionLocalService svnRevisionLocalService;
	protected SVNRevisionPersistence svnRevisionPersistence;
	protected WallEntryLocalService wallEntryLocalService;
	protected WallEntryPersistence wallEntryPersistence;
	protected WallEntryFinder wallEntryFinder;
}